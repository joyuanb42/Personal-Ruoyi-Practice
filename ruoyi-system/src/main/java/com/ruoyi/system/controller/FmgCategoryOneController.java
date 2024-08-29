package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Company;
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
import com.ruoyi.system.domain.FmgCategoryOne;
import com.ruoyi.system.service.IFmgCategoryOneService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 一级分类Controller
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@RestController
@RequestMapping("/system/CategoryOne")
public class FmgCategoryOneController extends BaseController
{
    @Autowired
    private IFmgCategoryOneService fmgCategoryOneService;

    /**
     * 3.查询一级分类列表全查
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<FmgCategoryOne> list = fmgCategoryOneService.selectfmgCategoryOneListAll();
        return success(list);
    }

    /**
     * 查询一级分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgCategoryOne fmgCategoryOne)
    {
        startPage();
        List<FmgCategoryOne> list = fmgCategoryOneService.selectFmgCategoryOneList(fmgCategoryOne);
        return getDataTable(list);
    }

    /**
     * 导出一级分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:export')")
    @Log(title = "一级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgCategoryOne fmgCategoryOne)
    {
        List<FmgCategoryOne> list = fmgCategoryOneService.selectFmgCategoryOneList(fmgCategoryOne);
        ExcelUtil<FmgCategoryOne> util = new ExcelUtil<FmgCategoryOne>(FmgCategoryOne.class);
        util.exportExcel(response, list, "一级分类数据");
    }

    /**
     * 获取一级分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:query')")
    @GetMapping(value = "/{coneId}")
    public AjaxResult getInfo(@PathVariable("coneId") Long coneId)
    {
        return success(fmgCategoryOneService.selectFmgCategoryOneByConeId(coneId));
    }

    /**
     * 新增一级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:add')")
    @Log(title = "一级分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgCategoryOne fmgCategoryOne)
    {
        return toAjax(fmgCategoryOneService.insertFmgCategoryOne(fmgCategoryOne));
    }

    /**
     * 修改一级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:edit')")
    @Log(title = "一级分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgCategoryOne fmgCategoryOne)
    {
        return toAjax(fmgCategoryOneService.updateFmgCategoryOne(fmgCategoryOne));
    }

    /**
     * 删除一级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryOne:remove')")
    @Log(title = "一级分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{coneIds}")
    public AjaxResult remove(@PathVariable Long[] coneIds)
    {
        return toAjax(fmgCategoryOneService.deleteFmgCategoryOneByConeIds(coneIds));
    }
}
