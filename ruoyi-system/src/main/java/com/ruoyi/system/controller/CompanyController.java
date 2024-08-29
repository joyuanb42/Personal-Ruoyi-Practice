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
import com.ruoyi.system.domain.Company;
import com.ruoyi.system.service.ICompanyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公司Controller
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/Company")
public class CompanyController extends BaseController
{
    @Autowired
    private ICompanyService companyService;

    @PreAuthorize("@ss.hasPermi('system:Company:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<Company> list = companyService.selectCompanyListAll();
        return success(list);
    }

    /**
     * 查询公司列表
     */
    @PreAuthorize("@ss.hasPermi('system:Company:list')")
    @GetMapping("/list")
    public TableDataInfo list(Company company)
    {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    /**
     * 导出公司列表
     */
    @PreAuthorize("@ss.hasPermi('system:Company:export')")
    @Log(title = "公司", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Company company)
    {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        util.exportExcel(response, list, "公司数据");
    }

    /**
     * 获取公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:Company:query')")
    @GetMapping(value = "/{coId}")
    public AjaxResult getInfo(@PathVariable("coId") Long coId)
    {
        return success(companyService.selectCompanyByCoId(coId));
    }

    /**
     * 新增公司
     */
    @PreAuthorize("@ss.hasPermi('system:Company:add')")
    @Log(title = "公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Company company)
    {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改公司
     */
    @PreAuthorize("@ss.hasPermi('system:Company:edit')")
    @Log(title = "公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Company company)
    {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除公司
     */
    @PreAuthorize("@ss.hasPermi('system:Company:remove')")
    @Log(title = "公司", businessType = BusinessType.DELETE)
	@DeleteMapping("/{coIds}")
    public AjaxResult remove(@PathVariable Long[] coIds)
    {
        return toAjax(companyService.deleteCompanyByCoIds(coIds));
    }
}
