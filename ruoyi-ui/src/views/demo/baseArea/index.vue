<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="地区名称" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入地区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地区代号" prop="areaCode">
        <el-input
          v-model="queryParams.areaCode"
          placeholder="请输入地区代号"
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
          v-hasPermi="['demo:baseArea:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="baseAreaList"
      row-key="areaId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="所属地区" prop="areaParId" />
      <el-table-column label="地区名称" align="center" prop="areaName" />
      <el-table-column label="地区代号" align="center" prop="areaCode" />
      <el-table-column label="区等级" align="center" prop="areaLevel" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['demo:baseArea:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['demo:baseArea:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['demo:baseArea:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改地区对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属地区" prop="areaParId">
          <treeselect v-model="form.areaParId" :options="baseAreaOptions" :normalizer="normalizer" placeholder="请选择所属地区" />
        </el-form-item>
        <el-form-item label="地区名称" prop="areaName">
          <el-input v-model="form.areaName" placeholder="请输入地区名称" />
        </el-form-item>
        <el-form-item label="地区代号" prop="areaCode">
          <el-input v-model="form.areaCode" placeholder="请输入地区代号" />
        </el-form-item>
        <el-form-item label="区等级" prop="areaLevel">
          <el-input v-model="form.areaLevel" placeholder="请输入区等级" />
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
import { listBaseArea, getBaseArea, delBaseArea, addBaseArea, updateBaseArea } from "@/api/demo/baseArea";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "BaseArea",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 地区表格数据
      baseAreaList: [],
      // 地区树选项
      baseAreaOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        areaName: null,
        areaCode: null,
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
  },
  methods: {
    /** 查询地区列表 */
    getList() {
      this.loading = true;
      listBaseArea(this.queryParams).then(response => {
        this.baseAreaList = this.handleTree(response.data, "areaId", "areaParId");
        this.loading = false;
      });
    },
    /** 转换地区数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.areaId,
        label: node.areaName,
        children: node.children
      };
    },
	/** 查询地区下拉树结构 */
    getTreeselect() {
      listBaseArea().then(response => {
        this.baseAreaOptions = [];
        const data = { areaId: 0, areaName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "areaId", "areaParId");
        this.baseAreaOptions.push(data);
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
        areaId: null,
        areaParId: null,
        areaName: null,
        areaCode: null,
        areaLevel: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.areaId) {
        this.form.areaParId = row.areaId;
      } else {
        this.form.areaParId = 0;
      }
      this.open = true;
      this.title = "添加地区";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.areaParId = row.areaParId;
      }
      getBaseArea(row.areaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改地区";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.areaId != null) {
            updateBaseArea(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBaseArea(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除地区编号为"' + row.areaId + '"的数据项？').then(function() {
        return delBaseArea(row.areaId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
