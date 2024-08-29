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
import com.ruoyi.system.domain.Team;
import com.ruoyi.system.service.ITeamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队Controller
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@RestController
@RequestMapping("/system/team")
public class TeamController extends BaseController
{
    @Autowired
    private ITeamService teamService;

    /**
     * 查询团队列表
     */
    @PreAuthorize("@ss.hasPermi('system:team:list')")
    @GetMapping("/list")
    public TableDataInfo list(Team team)
    {
        startPage();
        List<Team> list = teamService.selectTeamList(team);
        return getDataTable(list);
    }

    /**
     * 导出团队列表
     */
    @PreAuthorize("@ss.hasPermi('system:team:export')")
    @Log(title = "团队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Team team)
    {
        List<Team> list = teamService.selectTeamList(team);
        ExcelUtil<Team> util = new ExcelUtil<Team>(Team.class);
        util.exportExcel(response, list, "团队数据");
    }

    /**
     * 获取团队详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:team:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return success(teamService.selectTeamByTeamId(teamId));
    }

    /**
     * 新增团队
     */
    @PreAuthorize("@ss.hasPermi('system:team:add')")
    @Log(title = "团队", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Team team)
    {
        return toAjax(teamService.insertTeam(team));
    }

    /**
     * 修改团队
     */
    @PreAuthorize("@ss.hasPermi('system:team:edit')")
    @Log(title = "团队", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Team team)
    {
        return toAjax(teamService.updateTeam(team));
    }

    /**
     * 删除团队
     */
    @PreAuthorize("@ss.hasPermi('system:team:remove')")
    @Log(title = "团队", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        return toAjax(teamService.deleteTeamByTeamIds(teamIds));
    }
}
