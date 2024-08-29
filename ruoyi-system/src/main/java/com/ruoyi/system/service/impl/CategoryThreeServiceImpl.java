package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CategoryThreeMapper;
import com.ruoyi.system.domain.CategoryThree;
import com.ruoyi.system.service.ICategoryThreeService;

/**
 * 三级分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class CategoryThreeServiceImpl implements ICategoryThreeService 
{
    @Autowired
    private CategoryThreeMapper categoryThreeMapper;

    /**
     * 查询三级分类
     * 
     * @param cthrId 三级分类主键
     * @return 三级分类
     */
    @Override
    public CategoryThree selectCategoryThreeByCthrId(Long cthrId)
    {
        return categoryThreeMapper.selectCategoryThreeByCthrId(cthrId);
    }

    /**
     * 查询三级分类列表
     * 
     * @param categoryThree 三级分类
     * @return 三级分类
     */
    @Override
    public List<CategoryThree> selectCategoryThreeList(CategoryThree categoryThree)
    {
        return categoryThreeMapper.selectCategoryThreeList(categoryThree);
    }

    /**
     * 新增三级分类
     * 
     * @param categoryThree 三级分类
     * @return 结果
     */
    @Override
    public int insertCategoryThree(CategoryThree categoryThree)
    {
        return categoryThreeMapper.insertCategoryThree(categoryThree);
    }

    /**
     * 修改三级分类
     * 
     * @param categoryThree 三级分类
     * @return 结果
     */
    @Override
    public int updateCategoryThree(CategoryThree categoryThree)
    {
        return categoryThreeMapper.updateCategoryThree(categoryThree);
    }

    /**
     * 批量删除三级分类
     * 
     * @param cthrIds 需要删除的三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryThreeByCthrIds(Long[] cthrIds)
    {
        return categoryThreeMapper.deleteCategoryThreeByCthrIds(cthrIds);
    }

    /**
     * 删除三级分类信息
     * 
     * @param cthrId 三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryThreeByCthrId(Long cthrId)
    {
        return categoryThreeMapper.deleteCategoryThreeByCthrId(cthrId);
    }

    @Override
    public List<CategoryThree> selectCategoryThreeListAll() {
        return categoryThreeMapper.selectListAll();
    }
}
