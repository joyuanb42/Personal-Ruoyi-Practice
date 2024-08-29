<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成立时间" prop="supplierFoundedDate">
        <el-date-picker clearable
          v-model="queryParams.supplierFoundedDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择成立时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="注册资金" prop="supplierRegisteredCapital">
        <el-input
          v-model="queryParams.supplierRegisteredCapital"
          placeholder="请输入注册资金"
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
          v-hasPermi="['system:SupplierInfo:add']"
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
          v-hasPermi="['system:SupplierInfo:edit']"
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
          v-hasPermi="['system:SupplierInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:SupplierInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SupplierInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="供应商主键" align="center" prop="supplierId" />
      <el-table-column label="名称" align="center" prop="supplierName" />
      <el-table-column label="联系人" align="center" prop="supplierLinkPerson" />
      <el-table-column label="电话" align="center" prop="supplierPhone" />
      <el-table-column label="序号" align="center" prop="supplierNum" />
      <el-table-column label="银行名称" align="center" prop="bankName" />
      <el-table-column label="账号" align="center" prop="bankSn" />
      <el-table-column label="地址" align="center" prop="supplierAddress" />
      <el-table-column label="状态" align="center" prop="supplierState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.supplier_state" :value="scope.row.supplierState"/>
        </template>
      </el-table-column>
      <el-table-column label="代号" align="center" prop="supplierCode" />
      <el-table-column label="是否入住" align="center" prop="supplierIsJoin">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.supplier_is_join" :value="scope.row.supplierIsJoin"/>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" :formatter="stringFormat" prop="supplierInfo" />
      <el-table-column label="成立时间" align="center" prop="supplierFoundedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.supplierFoundedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册资金" align="center" prop="supplierRegisteredCapital" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:SupplierInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:SupplierInfo:remove']"
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

    <!-- 添加或修改供应商对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="supplierLinkPerson">
          <el-input v-model="form.supplierLinkPerson" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="电话" prop="supplierPhone">
          <el-input v-model="form.supplierPhone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="序号" prop="supplierNum">
          <el-input v-model="form.supplierNum" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="银行名称" prop="bankName">
          <el-input v-model="form.bankName" placeholder="请输入银行名称" />
        </el-form-item>
        <el-form-item label="账号" prop="bankSn">
          <el-input v-model="form.bankSn" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="地址" prop="supplierAddress">
          <el-input v-model="form.supplierAddress" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="supplierState">
          <el-radio-group v-model="form.supplierState">
            <el-radio
              v-for="dict in dict.type.supplier_state"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="代号" prop="supplierCode">
          <el-input v-model="form.supplierCode" placeholder="请输入代号" />
        </el-form-item>
        <el-form-item label="是否入住" prop="supplierIsJoin">
          <el-radio-group v-model="form.supplierIsJoin">
            <el-radio
              v-for="dict in dict.type.supplier_is_join"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="描述" prop="supplierInfo">
          <el-input v-model="form.supplierInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="成立时间" prop="supplierFoundedDate">
          <el-date-picker clearable
            v-model="form.supplierFoundedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择成立时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注册资金" prop="supplierRegisteredCapital">
          <el-input v-model="form.supplierRegisteredCapital" placeholder="请输入注册资金" />
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
import { listSupplierInfo, getSupplierInfo, delSupplierInfo, addSupplierInfo, updateSupplierInfo } from "@/api/system/SupplierInfo";

export default {
  name: "SupplierInfo",
  dicts: ['supplier_state', 'supplier_is_join'],
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
      // 供应商表格数据
      SupplierInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierName: null,
        supplierFoundedDate: null,
        supplierRegisteredCapital: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        supplierName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    stringFormat(a,b,c,d){
      return c.substring(0,2) + `...`
    },
    /** 查询供应商列表 */
    getList() {
      this.loading = true;
      listSupplierInfo(this.queryParams).then(response => {
        this.SupplierInfoList = response.rows;
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
        supplierId: null,
        supplierName: null,
        supplierLinkPerson: null,
        supplierPhone: null,
        supplierNum: null,
        bankName: null,
        bankSn: null,
        supplierAddress: null,
        supplierState: null,
        supplierCode: null,
        supplierIsJoin: null,
        supplierInfo: null,
        supplierFoundedDate: null,
        supplierRegisteredCapital: null
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
      this.ids = selection.map(item => item.supplierId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加供应商";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const supplierId = row.supplierId || this.ids
      getSupplierInfo(supplierId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改供应商";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.supplierId != null) {
            updateSupplierInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSupplierInfo(this.form).then(response => {
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
      const supplierIds = row.supplierId || this.ids;
      this.$modal.confirm('是否确认删除供应商编号为"' + supplierIds + '"的数据项？').then(function() {
        return delSupplierInfo(supplierIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/SupplierInfo/export', {
        ...this.queryParams
      }, `SupplierInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
