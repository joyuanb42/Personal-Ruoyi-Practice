package com.ruoyi.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.demo.mapper.BaseAreaMapper;
import com.ruoyi.demo.domain.BaseArea;
import com.ruoyi.demo.service.IBaseAreaService;

/**
 * 地区Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
@Service
public class BaseAreaServiceImpl implements IBaseAreaService 
{
    @Autowired
    private BaseAreaMapper baseAreaMapper;

    /**
     * 查询地区
     * 
     * @param areaId 地区主键
     * @return 地区
     */
    @Override
    public BaseArea selectBaseAreaByAreaId(Long areaId)
    {
        return baseAreaMapper.selectBaseAreaByAreaId(areaId);
    }

    /**
     * 查询地区列表
     * 
     * @param baseArea 地区
     * @return 地区
     */
    @Override
    public List<BaseArea> selectBaseAreaList(BaseArea baseArea)
    {
        return baseAreaMapper.selectBaseAreaList(baseArea);
    }

    /**
     * 新增地区
     * 
     * @param baseArea 地区
     * @return 结果
     */
    @Override
    public int insertBaseArea(BaseArea baseArea)
    {
        return baseAreaMapper.insertBaseArea(baseArea);
    }

    /**
     * 修改地区
     * 
     * @param baseArea 地区
     * @return 结果
     */
    @Override
    public int updateBaseArea(BaseArea baseArea)
    {
        return baseAreaMapper.updateBaseArea(baseArea);
    }

    /**
     * 批量删除地区
     * 
     * @param areaIds 需要删除的地区主键
     * @return 结果
     */
    @Override
    public int deleteBaseAreaByAreaIds(Long[] areaIds)
    {
        return baseAreaMapper.deleteBaseAreaByAreaIds(areaIds);
    }

    /**
     * 删除地区信息
     * 
     * @param areaId 地区主键
     * @return 结果
     */
    @Override
    public int deleteBaseAreaByAreaId(Long areaId)
    {
        return baseAreaMapper.deleteBaseAreaByAreaId(areaId);
    }

    @Override
    public List<Map<String, Object>> getData() {
        List<BaseArea> list = baseAreaMapper.selectBaseAreaListAll();
        List<Map<String, Object>> dataList = new ArrayList<>();
        dataList = list.stream().filter(baseArea -> baseArea.getAreaLevel()==1)
                .map(baseArea ->{
                    Map<String,Object> map = new HashMap();
                    map.put("label",baseArea.getAreaName());

                    List<Map<String,Object>> children = getChildren(baseArea,list);
                    if (children != null && children.size() > 0){
                        map.put("children",children);
                    }
                    return map;
                }).collect(Collectors.toList());
        return dataList;
    }

    private List<Map<String, Object>> getChildren(BaseArea parBaseArea, List<BaseArea> list) {
        return list.stream().filter(baseArea -> parBaseArea.getAreaId().equals(baseArea.getAreaParId()))
                .map(baseArea ->{
                    Map<String,Object> map = new HashMap();
                    map.put("label",baseArea.getAreaName());

                    List<Map<String,Object>> children = getChildren(baseArea,list);
                    if (children != null && children.size() > 0){
                        map.put("children",children);
                    }
                    return map;
                }).collect(Collectors.toList());
    }
}
