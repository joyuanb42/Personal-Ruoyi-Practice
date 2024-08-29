<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名称" prop="ctwoName">
        <el-input
          v-model="queryParams.ctwoName"
          placeholder="请输入分类名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类代号" prop="ctwoCode">
        <el-input
          v-model="queryParams.ctwoCode"
          placeholder="请输入分类代号"
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
          v-hasPermi="['system:CategoryTwo:add']"
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
          v-hasPermi="['system:CategoryTwo:edit']"
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
          v-hasPermi="['system:CategoryTwo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:CategoryTwo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="CategoryTwoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="二级分类主键" align="center" prop="ctwoId" />
      <el-table-column label="一级分类" align="center" prop="coneName" />
      <el-table-column label="分类名称" align="center" prop="ctwoName" />
      <el-table-column label="分类序号" align="center" prop="ctwoNum" />
      <el-table-column label="分类代号" align="center" prop="ctwoCode" />
      <el-table-column label="分类说明" align="center" prop="ctwoInfo" />
      <el-table-column label="一级分类名称" align="center" prop="coneName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:CategoryTwo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:CategoryTwo:remove']"
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

    <!-- 添加或修改二级分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="一级分类" prop="coneId">
<!--          1.一级分类下拉框-->
          <el-select v-model="form.coneId" placeholder="一级分类">
            <el-option
              v-for="item in options"
              :key="item.coneId"
              :label="item.coneName"
              :value="item.coneId">
            </el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="分类名称" prop="ctwoName">
          <el-input v-model="form.ctwoName" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="分类序号" prop="ctwoNum">
          <el-input v-model="form.ctwoNum" placeholder="请输入分类序号" />
        </el-form-item>
        <el-form-item label="分类代号" prop="ctwoCode">
          <el-input v-model="form.ctwoCode" placeholder="请输入分类代号" />
        </el-form-item>
        <el-form-item label="分类说明" prop="ctwoInfo">
          <el-input v-model="form.ctwoInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="一级分类名称" prop="coneName">
          <el-input v-model="form.coneName" placeholder="请输入一级分类名称" />
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
import { listCategoryTwo, getCategoryTwo, delCategoryTwo, addCategoryTwo, updateCategoryTwo } from "@/api/system/CategoryTwo";
// 5. 引入一级分类接口
import { listCategoryOneAll } from "@/api/system/CategoryOne";

export default {
  name: "CategoryTwo",
  data() {
    return {
      // 2.下拉框数据
      options : [],
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
      // 二级分类表格数据
      CategoryTwoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ctwoName: null,
        ctwoCode: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ctwoName: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    // 6.调用一级分类接口
    this.listCategoryOne();
  },
  methods: {
    listCategoryOne(){
      // 7.调用一级分类接口
      listCategoryOneAll().then(response => {
        this.options = response.data;
      });
    },
    /** 查询二级分类列表 */
    getList() {
      this.loading = true;
      listCategoryTwo(this.queryParams).then(response => {
        this.CategoryTwoList = response.rows;
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
        ctwoId: null,
        coneId: null,
        ctwoName: null,
        ctwoNum: null,
        ctwoCode: null,
        ctwoInfo: null,
        coneName: null
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
      this.ids = selection.map(item => item.ctwoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加二级分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ctwoId = row.ctwoId || this.ids
      getCategoryTwo(ctwoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改二级分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ctwoId != null) {
            updateCategoryTwo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCategoryTwo(this.form).then(response => {
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
      const ctwoIds = row.ctwoId || this.ids;
      this.$modal.confirm('是否确认删除二级分类编号为"' + ctwoIds + '"的数据项？').then(function() {
        return delCategoryTwo(ctwoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/CategoryTwo/export', {
        ...this.queryParams
      }, `CategoryTwo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
