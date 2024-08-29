package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FmgCategoryTwo;

/**
 * 二级分类Service接口
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public interface IFmgCategoryTwoService 
{
    /**
     * 查询二级分类
     * 
     * @param ctwoId 二级分类主键
     * @return 二级分类
     */
    public FmgCategoryTwo selectFmgCategoryTwoByCtwoId(Long ctwoId);

    /**
     * 查询二级分类列表
     * 
     * @param fmgCategoryTwo 二级分类
     * @return 二级分类集合
     */
    public List<FmgCategoryTwo> selectFmgCategoryTwoList(FmgCategoryTwo fmgCategoryTwo);

    /**
     * 新增二级分类
     * 
     * @param fmgCategoryTwo 二级分类
     * @return 结果
     */
    public int insertFmgCategoryTwo(FmgCategoryTwo fmgCategoryTwo);

    /**
     * 修改二级分类
     * 
     * @param fmgCategoryTwo 二级分类
     * @return 结果
     */
    public int updateFmgCategoryTwo(FmgCategoryTwo fmgCategoryTwo);

    /**
     * 批量删除二级分类
     * 
     * @param ctwoIds 需要删除的二级分类主键集合
     * @return 结果
     */
    public int deleteFmgCategoryTwoByCtwoIds(Long[] ctwoIds);

    /**
     * 删除二级分类信息
     * 
     * @param ctwoId 二级分类主键
     * @return 结果
     */
    public int deleteFmgCategoryTwoByCtwoId(Long ctwoId);

    List<FmgCategoryTwo> selectFmgCategoryTwoListAll();
}
