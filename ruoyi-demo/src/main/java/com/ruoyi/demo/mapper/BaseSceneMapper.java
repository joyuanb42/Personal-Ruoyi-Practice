package com.ruoyi.demo.mapper;

import java.util.List;
import com.ruoyi.demo.domain.BaseScene;

/**
 * 应用声明Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
public interface BaseSceneMapper 
{
    /**
     * 查询应用声明
     * 
     * @param sceneId 应用声明主键
     * @return 应用声明
     */
    public BaseScene selectBaseSceneBySceneId(Long sceneId);

    /**
     * 查询应用声明列表
     * 
     * @param baseScene 应用声明
     * @return 应用声明集合
     */
    public List<BaseScene> selectBaseSceneList(BaseScene baseScene);

    /**
     * 新增应用声明
     * 
     * @param baseScene 应用声明
     * @return 结果
     */
    public int insertBaseScene(BaseScene baseScene);

    /**
     * 修改应用声明
     * 
     * @param baseScene 应用声明
     * @return 结果
     */
    public int updateBaseScene(BaseScene baseScene);

    /**
     * 删除应用声明
     * 
     * @param sceneId 应用声明主键
     * @return 结果
     */
    public int deleteBaseSceneBySceneId(Long sceneId);

    /**
     * 批量删除应用声明
     * 
     * @param sceneIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseSceneBySceneIds(Long[] sceneIds);
}
