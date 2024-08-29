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
import com.ruoyi.system.domain.Author;
import com.ruoyi.system.service.IAuthorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作者Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/Author")
public class AuthorController extends BaseController
{
    @Autowired
    private IAuthorService authorService;

    /**
     * 查询作者列表
     */
    @PreAuthorize("@ss.hasPermi('system:Author:list')")
    @GetMapping("/list")
    public TableDataInfo list(Author author)
    {
        startPage();
        List<Author> list = authorService.selectAuthorList(author);
        return getDataTable(list);
    }

    /**
     * 导出作者列表
     */
    @PreAuthorize("@ss.hasPermi('system:Author:export')")
    @Log(title = "作者", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Author author)
    {
        List<Author> list = authorService.selectAuthorList(author);
        ExcelUtil<Author> util = new ExcelUtil<Author>(Author.class);
        util.exportExcel(response, list, "作者数据");
    }

    /**
     * 获取作者详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:Author:query')")
    @GetMapping(value = "/{authorId}")
    public AjaxResult getInfo(@PathVariable("authorId") Long authorId)
    {
        return success(authorService.selectAuthorByAuthorId(authorId));
    }

    /**
     * 新增作者
     */
    @PreAuthorize("@ss.hasPermi('system:Author:add')")
    @Log(title = "作者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Author author)
    {
        return toAjax(authorService.insertAuthor(author));
    }

    /**
     * 修改作者
     */
    @PreAuthorize("@ss.hasPermi('system:Author:edit')")
    @Log(title = "作者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Author author)
    {
        return toAjax(authorService.updateAuthor(author));
    }

    /**
     * 删除作者
     */
    @PreAuthorize("@ss.hasPermi('system:Author:remove')")
    @Log(title = "作者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{authorIds}")
    public AjaxResult remove(@PathVariable Long[] authorIds)
    {
        return toAjax(authorService.deleteAuthorByAuthorIds(authorIds));
    }
}
