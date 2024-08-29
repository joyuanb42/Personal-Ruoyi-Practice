<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="作者级别名称" prop="authorLevelName">
        <el-input
          v-model="queryParams.authorLevelName"
          placeholder="请输入作者级别名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者级别代号" prop="authorLevelCode">
        <el-input
          v-model="queryParams.authorLevelCode"
          placeholder="请输入作者级别代号"
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
          v-hasPermi="['system:AuthorLevel:add']"
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
          v-hasPermi="['system:AuthorLevel:edit']"
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
          v-hasPermi="['system:AuthorLevel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:AuthorLevel:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="AuthorLevelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="作者级别主键" align="center" prop="authorLevelId" />
      <el-table-column label="作者级别名称" align="center" prop="authorLevelName" />
      <el-table-column label="作者级别代号" align="center" prop="authorLevelCode" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:AuthorLevel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:AuthorLevel:remove']"
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

    <!-- 添加或修改作者级别对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作者级别名称" prop="authorLevelName">
          <el-input v-model="form.authorLevelName" placeholder="请输入作者级别名称" />
        </el-form-item>
        <el-form-item label="作者级别代号" prop="authorLevelCode">
          <el-input v-model="form.authorLevelCode" placeholder="请输入作者级别代号" />
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
import { listAuthorLevel, getAuthorLevel, delAuthorLevel, addAuthorLevel, updateAuthorLevel } from "@/api/system/AuthorLevel";

export default {
  name: "AuthorLevel",
  data() {
    return {
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
      // 作者级别表格数据
      AuthorLevelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        authorLevelName: null,
        authorLevelCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        authorLevelName: [
          { required: true, message: "作者级别名称不能为空", trigger: "blur" }
        ],
        authorLevelCode: [
          { required: true, message: "作者级别代号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询作者级别列表 */
    getList() {
      this.loading = true;
      listAuthorLevel(this.queryParams).then(response => {
        this.AuthorLevelList = response.rows;
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
        authorLevelId: null,
        authorLevelName: null,
        authorLevelCode: null
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
      this.ids = selection.map(item => item.authorLevelId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加作者级别";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const authorLevelId = row.authorLevelId || this.ids
      getAuthorLevel(authorLevelId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改作者级别";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.authorLevelId != null) {
            updateAuthorLevel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAuthorLevel(this.form).then(response => {
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
      const authorLevelIds = row.authorLevelId || this.ids;
      this.$modal.confirm('是否确认删除作者级别编号为"' + authorLevelIds + '"的数据项？').then(function() {
        return delAuthorLevel(authorLevelIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/AuthorLevel/export', {
        ...this.queryParams
      }, `AuthorLevel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
