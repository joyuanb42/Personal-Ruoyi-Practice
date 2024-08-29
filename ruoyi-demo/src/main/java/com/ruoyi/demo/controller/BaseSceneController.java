package com.ruoyi.demo.controller;

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
import com.ruoyi.demo.domain.BaseScene;
import com.ruoyi.demo.service.IBaseSceneService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应用声明Controller
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/demo/BaseScene")
public class BaseSceneController extends BaseController
{
    @Autowired
    private IBaseSceneService baseSceneService;

    /**
     * 查询应用声明列表
     */
    @PreAuthorize("@ss.hasPermi('demo:BaseScene:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseScene baseScene)
    {
        startPage();
        List<BaseScene> list = baseSceneService.selectBaseSceneList(baseScene);
        return getDataTable(list);
    }

    /**
     * 导出应用声明列表
     */
    @PreAuthorize("@ss.hasPermi('demo:BaseScene:export')")
    @Log(title = "应用声明", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseScene baseScene)
    {
        List<BaseScene> list = baseSceneService.selectBaseSceneList(baseScene);
        ExcelUtil<BaseScene> util = new ExcelUtil<BaseScene>(BaseScene.class);
        util.exportExcel(response, list, "应用声明数据");
    }

    /**
     * 获取应用声明详细信息
     */
    @PreAuthorize("@ss.hasPermi('demo:BaseScene:query')")
    @GetMapping(value = "/{sceneId}")
    public AjaxResult getInfo(@PathVariable("sceneId") Long sceneId)
    {
        return success(baseSceneService.selectBaseSceneBySceneId(sceneId));
    }

    /**
     * 新增应用声明
     */
    @PreAuthorize("@ss.hasPermi('demo:BaseScene:add')")
    @Log(title = "应用声明", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseScene baseScene)
    {
        return toAjax(baseSceneService.insertBaseScene(baseScene));
    }

    /**
     * 修改应用声明
     */
    @PreAuthorize("@ss.hasPermi('demo:BaseScene:edit')")
    @Log(title = "应用声明", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseScene baseScene)
    {
        return toAjax(baseSceneService.updateBaseScene(baseScene));
    }

    /**
     * 删除应用声明
     */
    @PreAuthorize("@ss.hasPermi('demo:BaseScene:remove')")
    @Log(title = "应用声明", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sceneIds}")
    public AjaxResult remove(@PathVariable Long[] sceneIds)
    {
        return toAjax(baseSceneService.deleteBaseSceneBySceneIds(sceneIds));
    }
}
