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
import com.ruoyi.system.domain.FmgCategoryTwo;
import com.ruoyi.system.service.IFmgCategoryTwoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二级分类Controller
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@RestController
@RequestMapping("/system/CategoryTwo")
public class FmgCategoryTwoController extends BaseController
{
    @Autowired
    private IFmgCategoryTwoService fmgCategoryTwoService;

    /**
     * 查询二级分类列表 全查
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<FmgCategoryTwo> list = fmgCategoryTwoService.selectFmgCategoryTwoListAll();
        return success(list);
    }

    /**
     * 查询二级分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmgCategoryTwo fmgCategoryTwo)
    {
        startPage();
        List<FmgCategoryTwo> list = fmgCategoryTwoService.selectFmgCategoryTwoList(fmgCategoryTwo);
        return getDataTable(list);
    }

    /**
     * 导出二级分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:export')")
    @Log(title = "二级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FmgCategoryTwo fmgCategoryTwo)
    {
        List<FmgCategoryTwo> list = fmgCategoryTwoService.selectFmgCategoryTwoList(fmgCategoryTwo);
        ExcelUtil<FmgCategoryTwo> util = new ExcelUtil<FmgCategoryTwo>(FmgCategoryTwo.class);
        util.exportExcel(response, list, "二级分类数据");
    }

    /**
     * 获取二级分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:query')")
    @GetMapping(value = "/{ctwoId}")
    public AjaxResult getInfo(@PathVariable("ctwoId") Long ctwoId)
    {
        return success(fmgCategoryTwoService.selectFmgCategoryTwoByCtwoId(ctwoId));
    }

    /**
     * 新增二级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:add')")
    @Log(title = "二级分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmgCategoryTwo fmgCategoryTwo)
    {
        return toAjax(fmgCategoryTwoService.insertFmgCategoryTwo(fmgCategoryTwo));
    }

    /**
     * 修改二级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:edit')")
    @Log(title = "二级分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmgCategoryTwo fmgCategoryTwo)
    {
        return toAjax(fmgCategoryTwoService.updateFmgCategoryTwo(fmgCategoryTwo));
    }

    /**
     * 删除二级分类
     */
    @PreAuthorize("@ss.hasPermi('system:CategoryTwo:remove')")
    @Log(title = "二级分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ctwoIds}")
    public AjaxResult remove(@PathVariable Long[] ctwoIds)
    {
        return toAjax(fmgCategoryTwoService.deleteFmgCategoryTwoByCtwoIds(ctwoIds));
    }
}
