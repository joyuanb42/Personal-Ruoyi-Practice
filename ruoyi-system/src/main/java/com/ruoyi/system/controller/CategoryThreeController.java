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
import com.ruoyi.system.domain.CategoryThree;
import com.ruoyi.system.service.ICategoryThreeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 三级分类Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/CategoryThree")
public class CategoryThreeController extends BaseController
{
    @Autowired
    private ICategoryThreeService categoryThreeService;

    /**
     * 查询三级分类列表 全查
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<CategoryThree> list = categoryThreeService.selectCategoryThreeListAll();
        return success(list);
    }

    /**
     * 查询三级分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryThree categoryThree)
    {
        startPage();
        List<CategoryThree> list = categoryThreeService.selectCategoryThreeList(categoryThree);
        return getDataTable(list);
    }

    /**
     * 导出三级分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:export')")
    @Log(title = "三级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryThree categoryThree)
    {
        List<CategoryThree> list = categoryThreeService.selectCategoryThreeList(categoryThree);
        ExcelUtil<CategoryThree> util = new ExcelUtil<CategoryThree>(CategoryThree.class);
        util.exportExcel(response, list, "三级分类数据");
    }

    /**
     * 获取三级分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:query')")
    @GetMapping(value = "/{cthrId}")
    public AjaxResult getInfo(@PathVariable("cthrId") Long cthrId)
    {
        return success(categoryThreeService.selectCategoryThreeByCthrId(cthrId));
    }

    /**
     * 新增三级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:add')")
    @Log(title = "三级分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryThree categoryThree)
    {
        return toAjax(categoryThreeService.insertCategoryThree(categoryThree));
    }

    /**
     * 修改三级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:edit')")
    @Log(title = "三级分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryThree categoryThree)
    {
        return toAjax(categoryThreeService.updateCategoryThree(categoryThree));
    }

    /**
     * 删除三级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryThree:remove')")
    @Log(title = "三级分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cthrIds}")
    public AjaxResult remove(@PathVariable Long[] cthrIds)
    {
        return toAjax(categoryThreeService.deleteCategoryThreeByCthrIds(cthrIds));
    }
}
