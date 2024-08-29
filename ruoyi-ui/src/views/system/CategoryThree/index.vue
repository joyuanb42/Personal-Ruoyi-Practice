<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名称" prop="cthrName">
        <el-input
          v-model="queryParams.cthrName"
          placeholder="请输入分类名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类代号" prop="cthrCode">
        <el-input
          v-model="queryParams.cthrCode"
          placeholder="请输入分类代号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二级分类名称" prop="ctwoName">
        <el-input
          v-model="queryParams.ctwoName"
          placeholder="请输入二级分类名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:CategoryThree:add']"
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
          v-hasPermi="['system:CategoryThree:edit']"
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
          v-hasPermi="['system:CategoryThree:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:CategoryThree:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="CategoryThreeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="三级分类主键" align="center" prop="cthrId" />
      <el-table-column label="一级分类" align="center" prop="coneName" />
      <el-table-column label="二级分类" align="center" prop="ctwoName" />
      <el-table-column label="分类名称" align="center" prop="cthrName" />
      <el-table-column label="分类序号" align="center" prop="cthrNum" />
      <el-table-column label="分类代号" align="center" prop="cthrCode" />
      <el-table-column label="分类说明" align="center" prop="cthrInfo" />
      <el-table-column label="一级分类名称" align="center" prop="coneName" />
      <el-table-column label="二级分类名称" align="center" prop="ctwoName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:CategoryThree:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:CategoryThree:remove']"
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

    <!-- 添加或修改三级分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="一级分类" prop="coneId">
          <el-select v-model="form.coneId" placeholder="一级分类">
            <el-option
              v-for="item in options1"
              :key="item.coneId"
              :label="item.coneName"
              :value="item.coneId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="二级分类" prop="ctwoId">
          <el-select v-model="form.ctwoId" placeholder="二级分类">
            <el-option
              v-for="item in options2"
              :key="item.ctwoId"
              :label="item.ctwoName"
              :value="item.ctwoId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类名称" prop="cthrName">
          <el-input v-model="form.cthrName" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="分类序号" prop="cthrNum">
          <el-input v-model="form.cthrNum" placeholder="请输入分类序号" />
        </el-form-item>
        <el-form-item label="分类代号" prop="cthrCode">
          <el-input v-model="form.cthrCode" placeholder="请输入分类代号" />
        </el-form-item>
        <el-form-item label="分类说明" prop="cthrInfo">
          <el-input v-model="form.cthrInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="一级分类名称" prop="coneName">
          <el-input v-model="form.coneName" placeholder="请输入一级分类名称" />
        </el-form-item>
        <el-form-item label="二级分类名称" prop="ctwoName">
          <el-input v-model="form.ctwoName" placeholder="请输入二级分类名称" />
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
import { listCategoryThree, getCategoryThree, delCategoryThree, addCategoryThree, updateCategoryThree } from "@/api/system/CategoryThree";
import { listCategoryOneAll } from "@/api/system/CategoryOne";
import { listCategoryTwoAll } from "@/api/system/CategoryTwo";

export default {
  name: "CategoryThree",
  data() {
    return {
      options1 : [],
      options2 : [],
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
      // 三级分类表格数据
      CategoryThreeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cthrName: null,
        cthrCode: null,
        ctwoName: null
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
    this.listCategoryOne();
    this.listCategoryTwo();
  },
  methods: {
    listCategoryOne(){
      // 7.调用一级分类接口
      listCategoryOneAll().then(response => {
        this.options1 = response.data;
      });
    },
    listCategoryTwo(){
      listCategoryTwoAll().then(response => {
        this.options2 = response.data;
      });
    },
    /** 查询三级分类列表 */
    getList() {
      this.loading = true;
      listCategoryThree(this.queryParams).then(response => {
        this.CategoryThreeList = response.rows;
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
        cthrId: null,
        coneId: null,
        ctwoId: null,
        cthrName: null,
        cthrNum: null,
        cthrCode: null,
        cthrInfo: null,
        coneName: null,
        ctwoName: null
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
      this.ids = selection.map(item => item.cthrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加三级分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cthrId = row.cthrId || this.ids
      getCategoryThree(cthrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改三级分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cthrId != null) {
            updateCategoryThree(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCategoryThree(this.form).then(response => {
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
      const cthrIds = row.cthrId || this.ids;
      this.$modal.confirm('是否确认删除三级分类编号为"' + cthrIds + '"的数据项？').then(function() {
        return delCategoryThree(cthrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/CategoryThree/export', {
        ...this.queryParams
      }, `CategoryThree_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
