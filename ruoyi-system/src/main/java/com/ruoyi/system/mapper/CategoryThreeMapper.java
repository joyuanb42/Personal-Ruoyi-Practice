package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CategoryThree;

/**
 * 三级分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface CategoryThreeMapper 
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
     * 删除三级分类
     * 
     * @param cthrId 三级分类主键
     * @return 结果
     */
    public int deleteCategoryThreeByCthrId(Long cthrId);

    /**
     * 批量删除三级分类
     * 
     * @param cthrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryThreeByCthrIds(Long[] cthrIds);

    List<CategoryThree> selectListAll();
}
