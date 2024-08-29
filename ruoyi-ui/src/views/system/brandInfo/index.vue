<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="中文名" prop="brandCnName">
        <el-input
          v-model="queryParams.brandCnName"
          placeholder="请输入中文名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="英文名" prop="brandEnName">
        <el-input
          v-model="queryParams.brandEnName"
          placeholder="请输入英文名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成立时间">
        <el-date-picker
          v-model="daterangeBrandFoundedDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['system:brandInfo:add']"
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
          v-hasPermi="['system:brandInfo:edit']"
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
          v-hasPermi="['system:brandInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:brandInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="brandInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="品牌主键" align="center" prop="brandId" />
      <el-table-column label="中文名" align="center" prop="brandCnName" />
      <el-table-column label="英文名" align="center" prop="brandEnName" />
      <el-table-column label="logo路径" align="center" prop="brandLogoPath" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.brandLogoPath" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="网址" align="center" prop="brandWebUrl" />
      <el-table-column label="权重" align="center" prop="brandNum" />
      <el-table-column label="说明" align="center" prop="brandInfo" />
      <el-table-column label="成立时间" align="center" :formatter="handlerFormatter" prop="brandFoundedDate" width="180">

      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:brandInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:brandInfo:remove']"
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

    <!-- 添加或修改品牌对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="中文名" prop="brandCnName">
          <el-input v-model="form.brandCnName" placeholder="请输入中文名" />
        </el-form-item>
        <el-form-item label="英文名" prop="brandEnName">
          <el-input v-model="form.brandEnName" placeholder="请输入英文名" />
        </el-form-item>
        <el-form-item label="logo路径" prop="brandLogoPath">
          <image-upload v-model="form.brandLogoPath"/>
        </el-form-item>
        <el-form-item label="网址" prop="brandWebUrl">
          <el-input v-model="form.brandWebUrl" placeholder="请输入网址" />
        </el-form-item>
        <el-form-item label="权重" prop="brandNum">
          <el-input v-model="form.brandNum" placeholder="请输入权重" />
        </el-form-item>
        <el-form-item label="说明" prop="brandInfo">
          <el-input v-model="form.brandInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="成立时间" prop="brandFoundedDate">
          <el-date-picker clearable
            v-model="form.brandFoundedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择成立时间">
          </el-date-picker>
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
import { listBrandInfo, getBrandInfo, delBrandInfo, addBrandInfo, updateBrandInfo } from "@/api/system/brandInfo";

export default {
  name: "BrandInfo",
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
      // 品牌表格数据
      brandInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 成立时间时间范围
      daterangeBrandFoundedDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        brandCnName: null,
        brandEnName: null,
        brandFoundedDate: null
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
    handlerFormatter(row, column, cellValue, index){
      let date = new Date(cellValue)
      return date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日"
    },
    /** 查询品牌列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeBrandFoundedDate && '' != this.daterangeBrandFoundedDate) {
        this.queryParams.params["beginBrandFoundedDate"] = this.daterangeBrandFoundedDate[0];
        this.queryParams.params["endBrandFoundedDate"] = this.daterangeBrandFoundedDate[1];
      }
      listBrandInfo(this.queryParams).then(response => {
        this.brandInfoList = response.rows;
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
        brandId: null,
        brandCnName: null,
        brandEnName: null,
        brandLogoPath: null,
        brandWebUrl: null,
        brandNum: null,
        brandInfo: null,
        brandFoundedDate: null
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
      this.daterangeBrandFoundedDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.brandId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加品牌";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const brandId = row.brandId || this.ids
      getBrandInfo(brandId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改品牌";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.brandId != null) {
            updateBrandInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBrandInfo(this.form).then(response => {
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
      const brandIds = row.brandId || this.ids;
      this.$modal.confirm('是否确认删除品牌编号为"' + brandIds + '"的数据项？').then(function() {
        return delBrandInfo(brandIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/brandInfo/export', {
        ...this.queryParams
      }, `brandInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
