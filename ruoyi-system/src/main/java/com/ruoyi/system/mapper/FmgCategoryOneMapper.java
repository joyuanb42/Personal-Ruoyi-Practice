package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FmgCategoryOne;

/**
 * 一级分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public interface FmgCategoryOneMapper 
{
    /**
     * 查询一级分类
     * 
     * @param coneId 一级分类主键
     * @return 一级分类
     */
    public FmgCategoryOne selectFmgCategoryOneByConeId(Long coneId);

    /**
     * 查询一级分类列表
     * 
     * @param fmgCategoryOne 一级分类
     * @return 一级分类集合
     */
    public List<FmgCategoryOne> selectFmgCategoryOneList(FmgCategoryOne fmgCategoryOne);

    /**
     * 新增一级分类
     * 
     * @param fmgCategoryOne 一级分类
     * @return 结果
     */
    public int insertFmgCategoryOne(FmgCategoryOne fmgCategoryOne);

    /**
     * 修改一级分类
     * 
     * @param fmgCategoryOne 一级分类
     * @return 结果
     */
    public int updateFmgCategoryOne(FmgCategoryOne fmgCategoryOne);

    /**
     * 删除一级分类
     * 
     * @param coneId 一级分类主键
     * @return 结果
     */
    public int deleteFmgCategoryOneByConeId(Long coneId);

    /**
     * 批量删除一级分类
     * 
     * @param coneIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFmgCategoryOneByConeIds(Long[] coneIds);

    List<FmgCategoryOne> selectListAll();
}