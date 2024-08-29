package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeamMapper;
import com.ruoyi.system.domain.Team;
import com.ruoyi.system.service.ITeamService;

/**
 * 团队Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@Service
public class TeamServiceImpl implements ITeamService 
{
    @Autowired
    private TeamMapper teamMapper;

    /**
     * 查询团队
     * 
     * @param teamId 团队主键
     * @return 团队
     */
    @Override
    public Team selectTeamByTeamId(Long teamId)
    {
        return teamMapper.selectTeamByTeamId(teamId);
    }

    /**
     * 查询团队列表
     * 
     * @param team 团队
     * @return 团队
     */
    @Override
    public List<Team> selectTeamList(Team team)
    {
        return teamMapper.selectTeamList(team);
    }

    /**
     * 新增团队
     * 
     * @param team 团队
     * @return 结果
     */
    @Override
    public int insertTeam(Team team)
    {
        return teamMapper.insertTeam(team);
    }

    /**
     * 修改团队
     * 
     * @param team 团队
     * @return 结果
     */
    @Override
    public int updateTeam(Team team)
    {
        return teamMapper.updateTeam(team);
    }

    /**
     * 批量删除团队
     * 
     * @param teamIds 需要删除的团队主键
     * @return 结果
     */
    @Override
    public int deleteTeamByTeamIds(Long[] teamIds)
    {
        return teamMapper.deleteTeamByTeamIds(teamIds);
    }

    /**
     * 删除团队信息
     * 
     * @param teamId 团队主键
     * @return 结果
     */
    @Override
    public int deleteTeamByTeamId(Long teamId)
    {
        return teamMapper.deleteTeamByTeamId(teamId);
    }
}
