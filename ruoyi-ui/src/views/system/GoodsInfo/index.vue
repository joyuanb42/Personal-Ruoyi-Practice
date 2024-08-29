<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品代号" prop="goodsCode">
        <el-input
          v-model="queryParams.goodsCode"
          placeholder="请输入商品代号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否上架" prop="isUp">
        <el-select v-model="queryParams.isUp" placeholder="请选择是否上架" clearable>
          <el-option
            v-for="dict in dict.type.is_up"
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
          v-hasPermi="['system:GoodsInfo:add']"
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
          v-hasPermi="['system:GoodsInfo:edit']"
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
          v-hasPermi="['system:GoodsInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:GoodsInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="GoodsInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品主键" align="center" prop="goodsId" />
      <el-table-column label="一级分类" align="center" prop="coneName" />
      <el-table-column label="二级分类" align="center" prop="ctwoName" />
      <el-table-column label="三级分类" align="center" prop="cthrName" />
      <el-table-column label="品牌" align="center" prop="brandCnName" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品代号" align="center" prop="goodsCode" />
      <el-table-column label="录入时间" :formatter="dateFormat" align="center" prop="goodsInTime" width="180">

      </el-table-column>
      <el-table-column label="详细说明" :formatter="stringFormat" align="center" prop="goodsInfo" />
      <el-table-column label="最新修改时间" :formatter="dateFormat" align="center" prop="modifiedTime" width="180">

      </el-table-column>
<!--      <el-table-column label="一级分类名称" align="center" prop="coneName" />-->
<!--      <el-table-column label="二级分类名称" align="center" prop="ctwoName" />-->
<!--      <el-table-column label="三级分类名称" align="center" prop="cthrName" />-->
<!--      <el-table-column label="品牌中文名" align="center" prop="brandCnName" />-->
      <el-table-column label="是否上架" align="center" prop="isUp">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_up" :value="scope.row.isUp"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:GoodsInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:GoodsInfo:remove']"
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

    <!-- 添加或修改商品SPU对话框 -->
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
        <el-form-item label="三级分类" prop="cthrId">
          <el-select v-model="form.cthrId" placeholder="三级分类">
            <el-option
              v-for="item in options3"
              :key="item.cthrId"
              :label="item.cthrName"
              :value="item.cthrId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="品牌" prop="brdId">
          <el-select v-model="form.brdId" placeholder="品牌">
            <el-option
              v-for="item in options4"
              :key="item.brandId"
              :label="item.brandCnName"
              :value="item.brandId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品代号" prop="goodsCode">
          <el-input v-model="form.goodsCode" placeholder="请输入商品代号" />
        </el-form-item>
        <el-form-item label="录入时间" prop="goodsInTime">
          <el-date-picker clearable
            v-model="form.goodsInTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择录入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="详细说明" prop="goodsInfo">
          <el-input v-model="form.goodsInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="最新修改时间" prop="modifiedTime">
          <el-date-picker clearable
            v-model="form.modifiedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最新修改时间">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="一级分类名称" prop="coneName">-->
<!--          <el-input v-model="form.coneName" placeholder="请输入一级分类名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="二级分类名称" prop="ctwoName">-->
<!--          <el-input v-model="form.ctwoName" placeholder="请输入二级分类名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="三级分类名称" prop="cthrName">-->
<!--          <el-input v-model="form.cthrName" placeholder="请输入三级分类名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="品牌中文名" prop="brandCnName">-->
<!--          <el-input v-model="form.brandCnName" placeholder="请输入品牌中文名" />-->
<!--        </el-form-item>-->
        <el-form-item label="是否上架" prop="isUp">
          <el-radio-group v-model="form.isUp">
            <el-radio
              v-for="dict in dict.type.is_up"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listGoodsInfo, getGoodsInfo, delGoodsInfo, addGoodsInfo, updateGoodsInfo } from "@/api/system/GoodsInfo";
import { listCategoryOneAll } from "@/api/system/CategoryOne";
import { listCategoryTwoAll } from "@/api/system/CategoryTwo";
import { listCategoryThreeAll } from "@/api/system/CategoryThree";
import { listBrandInfoAll } from "@/api/system/brandInfo";

export default {
  name: "GoodsInfo",
  dicts: ['is_up'],
  data() {
    return {
      options1 : [],
      options2 : [],
      options3 : [],
      options4 : [],
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
      // 商品SPU表格数据
      GoodsInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        goodsCode: null,
        isUp: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        goodsCode: [
          { required: true, message: "商品代号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.listCategoryOne();
    this.listCategoryTwo();
    this.listCategoryThree();
    this.listBrandInfo();
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
    listCategoryThree(){
      listCategoryThreeAll().then(response => {
        this.options3 = response.data;
      });
    },
    listBrandInfo(){
      listBrandInfoAll().then(response => {
        this.options4 = response.data;
      });
    },
    dateFormat(row, column, cellValue, index){
      let date = new Date(cellValue)
      return date.getFullYear() + "年" + (date.getMonth()+1) + "月" + date.getDate() + "日"
    },
    stringFormat(a,b,c,d){
      return c.substring(0,3) + `...`
    },
    /** 查询商品SPU列表 */
    getList() {
      this.loading = true;
      listGoodsInfo(this.queryParams).then(response => {
        this.GoodsInfoList = response.rows;
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
        goodsId: null,
        coneId: null,
        ctwoId: null,
        cthrId: null,
        brdId: null,
        goodsName: null,
        goodsCode: null,
        goodsInTime: null,
        goodsInfo: null,
        modifiedTime: null,
        coneName: null,
        ctwoName: null,
        cthrName: null,
        brandCnName: null,
        isUp: null
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
      this.ids = selection.map(item => item.goodsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品SPU";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodsId = row.goodsId || this.ids
      getGoodsInfo(goodsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品SPU";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.goodsId != null) {
            updateGoodsInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoodsInfo(this.form).then(response => {
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
      const goodsIds = row.goodsId || this.ids;
      this.$modal.confirm('是否确认删除商品SPU编号为"' + goodsIds + '"的数据项？').then(function() {
        return delGoodsInfo(goodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/GoodsInfo/export', {
        ...this.queryParams
      }, `GoodsInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
