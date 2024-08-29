package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SupplierInfo;
import com.ruoyi.system.service.ISupplierInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/SupplierInfo")
public class SupplierInfoController extends BaseController
{
    @Autowired
    private ISupplierInfoService supplierInfoService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('system:SupplierInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplierInfo supplierInfo)
    {
        startPage();
        List<SupplierInfo> list = supplierInfoService.selectSupplierInfoList(supplierInfo);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @PreAuthorize("@ss.hasPermi('system:SupplierInfo:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplierInfo supplierInfo)
    {
        List<SupplierInfo> list = supplierInfoService.selectSupplierInfoList(supplierInfo);
        ExcelUtil<SupplierInfo> util = new ExcelUtil<SupplierInfo>(SupplierInfo.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:SupplierInfo:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return success(supplierInfoService.selectSupplierInfoBySupplierId(supplierId));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('system:SupplierInfo:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplierInfo supplierInfo)
    {
        return toAjax(supplierInfoService.insertSupplierInfo(supplierInfo));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('system:SupplierInfo:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplierInfo supplierInfo)
    {
        return toAjax(supplierInfoService.updateSupplierInfo(supplierInfo));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('system:SupplierInfo:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(supplierInfoService.deleteSupplierInfoBySupplierIds(supplierIds));
    }
}
