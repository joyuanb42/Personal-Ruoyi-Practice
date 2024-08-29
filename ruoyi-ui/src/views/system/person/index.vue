<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="人员姓名" prop="persName">
        <el-input
          v-model="queryParams.persName"
          placeholder="请输入人员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员性别" prop="persSex">
        <el-select v-model="queryParams.persSex" placeholder="请选择人员性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:person:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:person:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:person:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:person:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="personList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="人员主键" align="center" prop="persId" />
      <el-table-column label="公司" align="center" prop="coName" />
      <el-table-column label="人员姓名" align="center" prop="persName" />
      <el-table-column label="人员性别" align="center" prop="persSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.persSex"/>
        </template>
      </el-table-column>
      <el-table-column label="入职时间" :formatter="dateFormat" align="center" prop="persInDate" width="180">

      </el-table-column>
      <el-table-column label="薪水" align="center" prop="persSalary" />
      <el-table-column label="备注 最多500字" :formatter="stringFormat" align="center" prop="persRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:person:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:person:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改人员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司" prop="coId">
          <el-select v-model="form.coId" placeholder="请选择公司">
            <el-option
              v-for="item in options"
              :key="item.coId"
              :label="item.coName"
              :value="item.coId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="人员姓名" prop="persName">
          <el-input v-model="form.persName" placeholder="请输入人员姓名" />
        </el-form-item>
        <el-form-item label="人员性别" prop="persSex">
          <el-radio-group v-model="form.persSex">
            <el-radio
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="入职时间" prop="persInDate">
          <el-date-picker clearable
            v-model="form.persInDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="薪水" prop="persSalary">
          <el-input v-model="form.persSalary" placeholder="请输入薪水" />
        </el-form-item>
        <el-form-item label="备注 最多500字" prop="persRemark">
          <el-input v-model="form.persRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPerson, getPerson, delPerson, addPerson, updatePerson } from "@/api/system/person";
import { listCompanyAll } from "@/api/system/Company";

export default {
  name: "Person",
  dicts: ['sys_user_sex'],
  data() {
    return {
      options : [{
      }],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 人员表格数据
      personList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        persName: null,
        persSex: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getCompany();
  },
  methods: {
    getCompany(){
      listCompanyAll().then(response => {
        this.options = response.data
      })
    },
    dateFormat(row, column, cellValue, index){
      let date = new Date(cellValue)
      return date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日"
    },
    stringFormat(a,b,c,d){
      return c.substring(0,5) + `...`
    },
    /** 查询人员列表 */
    getList() {
      this.loading = true;
      listPerson(this.queryParams).then(response => {
        this.personList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        persId: null,
        coId: null,
        persName: null,
        persSex: null,
        persInDate: null,
        persSalary: null,
        persRemark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.persId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加人员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const persId = row.persId || this.ids
      getPerson(persId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改人员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.persId != null) {
            updatePerson(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPerson(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const persIds = row.persId || this.ids;
      this.$modal.confirm('是否确认删除人员编号为"' + persIds + '"的数据项？').then(function() {
        return delPerson(persIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/person/export', {
        ...this.queryParams
      }, `person_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
