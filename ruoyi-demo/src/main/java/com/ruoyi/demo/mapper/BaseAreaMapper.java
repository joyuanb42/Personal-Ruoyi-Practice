package com.ruoyi.demo.mapper;

import java.util.List;
import com.ruoyi.demo.domain.BaseArea;

/**
 * 地区Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
public interface BaseAreaMapper 
{
    /**
     * 查询地区
     * 
     * @param areaId 地区主键
     * @return 地区
     */
    public BaseArea selectBaseAreaByAreaId(Long areaId);

    /**
     * 查询地区列表
     * 
     * @param baseArea 地区
     * @return 地区集合
     */
    public List<BaseArea> selectBaseAreaList(BaseArea baseArea);

    /**
     * 新增地区
     * 
     * @param baseArea 地区
     * @return 结果
     */
    public int insertBaseArea(BaseArea baseArea);

    /**
     * 修改地区
     * 
     * @param baseArea 地区
     * @return 结果
     */
    public int updateBaseArea(BaseArea baseArea);

    /**
     * 删除地区
     * 
     * @param areaId 地区主键
     * @return 结果
     */
    public int deleteBaseAreaByAreaId(Long areaId);

    /**
     * 批量删除地区
     * 
     * @param areaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseAreaByAreaIds(Long[] areaIds);

    List<BaseArea> selectBaseAreaListAll();
}
