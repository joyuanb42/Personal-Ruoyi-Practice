package com.ruoyi.demo.controller;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.demo.domain.BaseArea;
import com.ruoyi.demo.service.IBaseAreaService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 地区Controller
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/demo/baseArea")
public class BaseAreaController extends BaseController
{
    @Autowired
    private IBaseAreaService baseAreaService;

    @PreAuthorize("@ss.hasPermi('demo:baseArea:list')")
    @GetMapping("/list")
    public AjaxResult list(BaseArea baseArea)
    {
        List<BaseArea> list = baseAreaService.selectBaseAreaList(baseArea);
        return success(list);
    }

    /**
     * 查询地区列表
     */
    @PreAuthorize("@ss.hasPermi('demo:baseArea:list')")
    @GetMapping("/getData")
    public AjaxResult getData()
    {
        List<Map<String,Object>> list = baseAreaService.getData();
        return success(list);
    }

    /**
     * 导出地区列表
     */
    @PreAuthorize("@ss.hasPermi('demo:baseArea:export')")
    @Log(title = "地区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseArea baseArea)
    {
        List<BaseArea> list = baseAreaService.selectBaseAreaList(baseArea);
        ExcelUtil<BaseArea> util = new ExcelUtil<BaseArea>(BaseArea.class);
        util.exportExcel(response, list, "地区数据");
    }

    /**
     * 获取地区详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:baseArea:query')")
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId)
    {
        return success(baseAreaService.selectBaseAreaByAreaId(areaId));
    }

    /**
     * 新增地区
     */
    @PreAuthorize("@ss.hasPermi('demo:baseArea:add')")
    @Log(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseArea baseArea)
    {
        return toAjax(baseAreaService.insertBaseArea(baseArea));
    }

    /**
     * 修改地区
     */
    @PreAuthorize("@ss.hasPermi('demo:baseArea:edit')")
    @Log(title = "地区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseArea baseArea)
    {
        return toAjax(baseAreaService.updateBaseArea(baseArea));
    }

    /**
     * 删除地区
     */
    @PreAuthorize("@ss.hasPermi('demo:baseArea:remove')")
    @Log(title = "地区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{areaIds}")
    public AjaxResult remove(@PathVariable Long[] areaIds)
    {
        return toAjax(baseAreaService.deleteBaseAreaByAreaIds(areaIds));
    }
}
