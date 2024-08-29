package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FmgCategoryTwo;

/**
 * 二级分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public interface FmgCategoryTwoMapper 
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
     * 删除二级分类
     * 
     * @param ctwoId 二级分类主键
     * @return 结果
     */
    public int deleteFmgCategoryTwoByCtwoId(Long ctwoId);

    /**
     * 批量删除二级分类
     * 
     * @param ctwoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgCategoryTwoByCtwoIds(Long[] ctwoIds);

    List<FmgCategoryTwo> selectListAll();
}
