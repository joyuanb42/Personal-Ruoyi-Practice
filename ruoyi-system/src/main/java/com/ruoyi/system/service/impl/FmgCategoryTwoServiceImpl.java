package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FmgCategoryTwoMapper;
import com.ruoyi.system.domain.FmgCategoryTwo;
import com.ruoyi.system.service.IFmgCategoryTwoService;

/**
 * 二级分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@Service
public class FmgCategoryTwoServiceImpl implements IFmgCategoryTwoService 
{
    @Autowired
    private FmgCategoryTwoMapper fmgCategoryTwoMapper;

    /**
     * 查询二级分类
     * 
     * @param ctwoId 二级分类主键
     * @return 二级分类
     */
    @Override
    public FmgCategoryTwo selectFmgCategoryTwoByCtwoId(Long ctwoId)
    {
        return fmgCategoryTwoMapper.selectFmgCategoryTwoByCtwoId(ctwoId);
    }

    /**
     * 查询二级分类列表
     * 
     * @param fmgCategoryTwo 二级分类
     * @return 二级分类
     */
    @Override
    public List<FmgCategoryTwo> selectFmgCategoryTwoList(FmgCategoryTwo fmgCategoryTwo)
    {
        return fmgCategoryTwoMapper.selectFmgCategoryTwoList(fmgCategoryTwo);
    }

    /**
     * 新增二级分类
     * 
     * @param fmgCategoryTwo 二级分类
     * @return 结果
     */
    @Override
    public int insertFmgCategoryTwo(FmgCategoryTwo fmgCategoryTwo)
    {
        return fmgCategoryTwoMapper.insertFmgCategoryTwo(fmgCategoryTwo);
    }

    /**
     * 修改二级分类
     * 
     * @param fmgCategoryTwo 二级分类
     * @return 结果
     */
    @Override
    public int updateFmgCategoryTwo(FmgCategoryTwo fmgCategoryTwo)
    {
        return fmgCategoryTwoMapper.updateFmgCategoryTwo(fmgCategoryTwo);
    }

    /**
     * 批量删除二级分类
     * 
     * @param ctwoIds 需要删除的二级分类主键
     * @return 结果
     */
    @Override
    public int deleteFmgCategoryTwoByCtwoIds(Long[] ctwoIds)
    {
        return fmgCategoryTwoMapper.deleteFmgCategoryTwoByCtwoIds(ctwoIds);
    }

    /**
     * 删除二级分类信息
     * 
     * @param ctwoId 二级分类主键
     * @return 结果
     */
    @Override
    public int deleteFmgCategoryTwoByCtwoId(Long ctwoId)
    {
        return fmgCategoryTwoMapper.deleteFmgCategoryTwoByCtwoId(ctwoId);
    }

    @Override
    public List<FmgCategoryTwo> selectFmgCategoryTwoListAll() {
        return fmgCategoryTwoMapper.selectListAll();
    }
}
