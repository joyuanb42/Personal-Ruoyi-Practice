<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="作者姓名" prop="authorName">
        <el-input
          v-model="queryParams.authorName"
          placeholder="请输入作者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者性别" prop="authorSex">
        <el-select v-model="queryParams.authorSex" placeholder="请选择作者性别" clearable>
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
          v-hasPermi="['system:Author:add']"
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
          v-hasPermi="['system:Author:edit']"
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
          v-hasPermi="['system:Author:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:Author:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="AuthorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="作者主键" align="center" prop="authorId" />
      <el-table-column label="作者级别" align="center" prop="authorLevelName" />
      <el-table-column label="作者姓名" align="center" prop="authorName" />
      <el-table-column label="作者性别" align="center" prop="authorSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.authorSex"/>
        </template>
      </el-table-column>
      <el-table-column label="作者生日" :formatter="dateFormat" align="center" prop="authorBirth" width="180">

      </el-table-column>
      <el-table-column label="作品数量" align="center" prop="authorWorksCount" />
      <el-table-column label="作者介绍    最多500字" :formatter="stringFormat" align="center" prop="authorInfo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:Author:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:Author:remove']"
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

    <!-- 添加或修改作者对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作者级别" prop="authorLevelId">
          <el-select v-model="form.authorLevelId" placeholder="作者级别">
            <el-option
              v-for="item in options"
              :key="item.authorLevelId"
              :label="item.authorLevelName"
              :value="item.authorLevelId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者姓名" prop="authorName">
          <el-input v-model="form.authorName" placeholder="请输入作者姓名" />
        </el-form-item>
<!--        <el-form-item label="作者性别" prop="authorSex">-->
<!--          <el-select v-model="form.authorSex" placeholder="请选择作者性别">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.sys_user_sex"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="parseInt(dict.value)"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="作者性别" prop="authorSex">
          <el-radio-group v-model="form.authorSex">
            <el-radio
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="作者生日" prop="authorBirth">
          <el-date-picker clearable
            v-model="form.authorBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择作者生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="作品数量" prop="authorWorksCount">
          <el-input v-model="form.authorWorksCount" placeholder="请输入作品数量" />
        </el-form-item>
        <el-form-item label="作者介绍   最多500字" prop="authorInfo">
          <el-input v-model="form.authorInfo" type="textarea" placeholder="请输入内容" />
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
import { listAuthor, getAuthor, delAuthor, addAuthor, updateAuthor } from "@/api/system/Author";
import { listAuthorLevelAll } from "@/api/system/AuthorLevel";
export default {
  name: "Author",
  dicts: ['sys_user_sex'],
  data() {
    return {
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
      // 作者表格数据
      AuthorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        authorName: null,
        authorSex: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        authorName: [
          { required: true, message: "作者姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.listAuthorLevelAll();
  },
  methods: {
    listAuthorLevelAll(){
      listAuthorLevelAll().then(response => {
        this.options = response.data
      })
    },
    dateFormat(row, column, cellValue, index){
      let date = new Date(cellValue)
      return date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日"
    },
    stringFormat(a,b,c,d){
      return c.substring(0,3) + `...`
    },
    /** 查询作者列表 */
    getList() {
      this.loading = true;
      listAuthor(this.queryParams).then(response => {
        this.AuthorList = response.rows;
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
        authorId: null,
        authorLevelId: null,
        authorName: null,
        authorSex: null,
        authorBirth: null,
        authorWorksCount: null,
        authorInfo: null
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
      this.ids = selection.map(item => item.authorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加作者";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const authorId = row.authorId || this.ids
      getAuthor(authorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改作者";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.authorId != null) {
            updateAuthor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAuthor(this.form).then(response => {
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
      const authorIds = row.authorId || this.ids;
      this.$modal.confirm('是否确认删除作者编号为"' + authorIds + '"的数据项？').then(function() {
        return delAuthor(authorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/Author/export', {
        ...this.queryParams
      }, `Author_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
