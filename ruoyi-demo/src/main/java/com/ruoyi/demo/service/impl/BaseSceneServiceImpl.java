package com.ruoyi.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demo.mapper.BaseSceneMapper;
import com.ruoyi.demo.domain.BaseScene;
import com.ruoyi.demo.service.IBaseSceneService;

/**
 * 应用声明Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
@Service
public class BaseSceneServiceImpl implements IBaseSceneService 
{
    @Autowired
    private BaseSceneMapper baseSceneMapper;

    /**
     * 查询应用声明
     * 
     * @param sceneId 应用声明主键
     * @return 应用声明
     */
    @Override
    public BaseScene selectBaseSceneBySceneId(Long sceneId)
    {
        return baseSceneMapper.selectBaseSceneBySceneId(sceneId);
    }

    /**
     * 查询应用声明列表
     * 
     * @param baseScene 应用声明
     * @return 应用声明
     */
    @Override
    public List<BaseScene> selectBaseSceneList(BaseScene baseScene)
    {
        return baseSceneMapper.selectBaseSceneList(baseScene);
    }

    /**
     * 新增应用声明
     * 
     * @param baseScene 应用声明
     * @return 结果
     */
    @Override
    public int insertBaseScene(BaseScene baseScene)
    {
        return baseSceneMapper.insertBaseScene(baseScene);
    }

    /**
     * 修改应用声明
     * 
     * @param baseScene 应用声明
     * @return 结果
     */
    @Override
    public int updateBaseScene(BaseScene baseScene)
    {
        return baseSceneMapper.updateBaseScene(baseScene);
    }

    /**
     * 批量删除应用声明
     * 
     * @param sceneIds 需要删除的应用声明主键
     * @return 结果
     */
    @Override
    public int deleteBaseSceneBySceneIds(Long[] sceneIds)
    {
        return baseSceneMapper.deleteBaseSceneBySceneIds(sceneIds);
    }

    /**
     * 删除应用声明信息
     * 
     * @param sceneId 应用声明主键
     * @return 结果
     */
    @Override
    public int deleteBaseSceneBySceneId(Long sceneId)
    {
        return baseSceneMapper.deleteBaseSceneBySceneId(sceneId);
    }
}
