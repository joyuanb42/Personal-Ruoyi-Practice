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
import com.ruoyi.system.domain.BrandInfo;
import com.ruoyi.system.service.IBrandInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 品牌Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/brandInfo")
public class BrandInfoController extends BaseController
{
    @Autowired
    private IBrandInfoService brandInfoService;

    /**
     * 查询品牌列表 全查
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<BrandInfo> list = brandInfoService.selectBrandInfoListAll();
        return success(list);
    }

    /**
     * 查询品牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrandInfo brandInfo)
    {
        startPage();
        List<BrandInfo> list = brandInfoService.selectBrandInfoList(brandInfo);
        return getDataTable(list);
    }

    /**
     * 导出品牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:export')")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrandInfo brandInfo)
    {
        List<BrandInfo> list = brandInfoService.selectBrandInfoList(brandInfo);
        ExcelUtil<BrandInfo> util = new ExcelUtil<BrandInfo>(BrandInfo.class);
        util.exportExcel(response, list, "品牌数据");
    }

    /**
     * 获取品牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:query')")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId)
    {
        return success(brandInfoService.selectBrandInfoByBrandId(brandId));
    }

    /**
     * 新增品牌
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:add')")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrandInfo brandInfo)
    {
        return toAjax(brandInfoService.insertBrandInfo(brandInfo));
    }

    /**
     * 修改品牌
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:edit')")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrandInfo brandInfo)
    {
        return toAjax(brandInfoService.updateBrandInfo(brandInfo));
    }

    /**
     * 删除品牌
     */
    @PreAuthorize("@ss.hasPermi('system:brandInfo:remove')")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds)
    {
        return toAjax(brandInfoService.deleteBrandInfoByBrandIds(brandIds));
    }
}
