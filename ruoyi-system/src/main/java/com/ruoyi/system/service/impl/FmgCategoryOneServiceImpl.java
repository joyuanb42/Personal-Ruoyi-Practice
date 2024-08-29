package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FmgCategoryOneMapper;
import com.ruoyi.system.domain.FmgCategoryOne;
import com.ruoyi.system.service.IFmgCategoryOneService;

/**
 * 一级分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@Service
public class FmgCategoryOneServiceImpl implements IFmgCategoryOneService 
{
    @Autowired
    private FmgCategoryOneMapper fmgCategoryOneMapper;

    /**
     * 查询一级分类
     * 
     * @param coneId 一级分类主键
     * @return 一级分类
     */
    @Override
    public FmgCategoryOne selectFmgCategoryOneByConeId(Long coneId)
    {
        return fmgCategoryOneMapper.selectFmgCategoryOneByConeId(coneId);
    }

    /**
     * 查询一级分类列表
     * 
     * @param fmgCategoryOne 一级分类
     * @return 一级分类
     */
    @Override
    public List<FmgCategoryOne> selectFmgCategoryOneList(FmgCategoryOne fmgCategoryOne)
    {
        return fmgCategoryOneMapper.selectFmgCategoryOneList(fmgCategoryOne);
    }

    /**
     * 新增一级分类
     * 
     * @param fmgCategoryOne 一级分类
     * @return 结果
     */
    @Override
    public int insertFmgCategoryOne(FmgCategoryOne fmgCategoryOne)
    {
        return fmgCategoryOneMapper.insertFmgCategoryOne(fmgCategoryOne);
    }

    /**
     * 修改一级分类
     * 
     * @param fmgCategoryOne 一级分类
     * @return 结果
     */
    @Override
    public int updateFmgCategoryOne(FmgCategoryOne fmgCategoryOne)
    {
        return fmgCategoryOneMapper.updateFmgCategoryOne(fmgCategoryOne);
    }

    /**
     * 批量删除一级分类
     * 
     * @param coneIds 需要删除的一级分类主键
     * @return 结果
     */
    @Override
    public int deleteFmgCategoryOneByConeIds(Long[] coneIds)
    {
        return fmgCategoryOneMapper.deleteFmgCategoryOneByConeIds(coneIds);
    }

    /**
     * 删除一级分类信息
     * 
     * @param coneId 一级分类主键
     * @return 结果
     */
    @Override
    public int deleteFmgCategoryOneByConeId(Long coneId)
    {
        return fmgCategoryOneMapper.deleteFmgCategoryOneByConeId(coneId);
    }

    @Override
    public List<FmgCategoryOne> selectfmgCategoryOneListAll() {
        return fmgCategoryOneMapper.selectListAll();
    }
}
