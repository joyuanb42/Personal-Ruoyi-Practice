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
import com.ruoyi.system.domain.AuthorLevel;
import com.ruoyi.system.service.IAuthorLevelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作者级别Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/AuthorLevel")
public class AuthorLevelController extends BaseController
{
    @Autowired
    private IAuthorLevelService authorLevelService;

    /**
     * 查询作者级别列表 全查
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<AuthorLevel> list = authorLevelService.selectAuthorLevelListAll();
        return success(list);
    }

    /**
     * 查询作者级别列表
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:list')")
    @GetMapping("/list")
    public TableDataInfo list(AuthorLevel authorLevel)
    {
        startPage();
        List<AuthorLevel> list = authorLevelService.selectAuthorLevelList(authorLevel);
        return getDataTable(list);
    }

    /**
     * 导出作者级别列表
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:export')")
    @Log(title = "作者级别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AuthorLevel authorLevel)
    {
        List<AuthorLevel> list = authorLevelService.selectAuthorLevelList(authorLevel);
        ExcelUtil<AuthorLevel> util = new ExcelUtil<AuthorLevel>(AuthorLevel.class);
        util.exportExcel(response, list, "作者级别数据");
    }

    /**
     * 获取作者级别详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:query')")
    @GetMapping(value = "/{authorLevelId}")
    public AjaxResult getInfo(@PathVariable("authorLevelId") Long authorLevelId)
    {
        return success(authorLevelService.selectAuthorLevelByAuthorLevelId(authorLevelId));
    }

    /**
     * 新增作者级别
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:add')")
    @Log(title = "作者级别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AuthorLevel authorLevel)
    {
        return toAjax(authorLevelService.insertAuthorLevel(authorLevel));
    }

    /**
     * 修改作者级别
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:edit')")
    @Log(title = "作者级别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AuthorLevel authorLevel)
    {
        return toAjax(authorLevelService.updateAuthorLevel(authorLevel));
    }

    /**
     * 删除作者级别
     */
    @PreAuthorize("@ss.hasPermi('system:AuthorLevel:remove')")
    @Log(title = "作者级别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{authorLevelIds}")
    public AjaxResult remove(@PathVariable Long[] authorLevelIds)
    {
        return toAjax(authorLevelService.deleteAuthorLevelByAuthorLevelIds(authorLevelIds));
    }
}
