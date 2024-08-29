package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CategoryThree;

/**
 * 三级分类Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface ICategoryThreeService 
{
    /**
     * 查询三级分类
     * 
     * @param cthrId 三级分类主键
     * @return 三级分类
     */
    public CategoryThree selectCategoryThreeByCthrId(Long cthrId);

    /**
     * 查询三级分类列表
     * 
     * @param categoryThree 三级分类
     * @return 三级分类集合
     */
    public List<CategoryThree> selectCategoryThreeList(CategoryThree categoryThree);

    /**
     * 新增三级分类
     * 
     * @param categoryThree 三级分类
     * @return 结果
     */
    public int insertCategoryThree(CategoryThree categoryThree);

    /**
     * 修改三级分类
     * 
     * @param categoryThree 三级分类
     * @return 结果
     */
    public int updateCategoryThree(CategoryThree categoryThree);

    /**
     * 批量删除三级分类
     * 
     * @param cthrIds 需要删除的三级分类主键集合
     * @return 结果
     */
    public int deleteCategoryThreeByCthrIds(Long[] cthrIds);

    /**
     * 删除三级分类信息
     * 
     * @param cthrId 三级分类主键
     * @return 结果
     */
    public int deleteCategoryThreeByCthrId(Long cthrId);

    List<CategoryThree> selectCategoryThreeListAll();
}
