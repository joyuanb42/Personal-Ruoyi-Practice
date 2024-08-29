package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Team;

/**
 * 团队Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public interface TeamMapper 
{
    /**
     * 查询团队
     * 
     * @param teamId 团队主键
     * @return 团队
     */
    public Team selectTeamByTeamId(Long teamId);

    /**
     * 查询团队列表
     * 
     * @param team 团队
     * @return 团队集合
     */
    public List<Team> selectTeamList(Team team);

    /**
     * 新增团队
     * 
     * @param team 团队
     * @return 结果
     */
    public int insertTeam(Team team);

    /**
     * 修改团队
     * 
     * @param team 团队
     * @return 结果
     */
    public int updateTeam(Team team);

    /**
     * 删除团队
     * 
     * @param teamId 团队主键
     * @return 结果
     */
    public int deleteTeamByTeamId(Long teamId);

    /**
     * 批量删除团队
     * 
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamByTeamIds(Long[] teamIds);
}
