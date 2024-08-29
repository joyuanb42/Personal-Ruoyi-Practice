package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AuthorLevel;

/**
 * 作者级别Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface IAuthorLevelService 
{
    /**
     * 查询作者级别
     * 
     * @param authorLevelId 作者级别主键
     * @return 作者级别
     */
    public AuthorLevel selectAuthorLevelByAuthorLevelId(Long authorLevelId);

    /**
     * 查询作者级别列表
     * 
     * @param authorLevel 作者级别
     * @return 作者级别集合
     */
    public List<AuthorLevel> selectAuthorLevelList(AuthorLevel authorLevel);

    /**
     * 新增作者级别
     * 
     * @param authorLevel 作者级别
     * @return 结果
     */
    public int insertAuthorLevel(AuthorLevel authorLevel);

    /**
     * 修改作者级别
     * 
     * @param authorLevel 作者级别
     * @return 结果
     */
    public int updateAuthorLevel(AuthorLevel authorLevel);

    /**
     * 批量删除作者级别
     * 
     * @param authorLevelIds 需要删除的作者级别主键集合
     * @return 结果
     */
    public int deleteAuthorLevelByAuthorLevelIds(Long[] authorLevelIds);

    /**
     * 删除作者级别信息
     * 
     * @param authorLevelId 作者级别主键
     * @return 结果
     */
    public int deleteAuthorLevelByAuthorLevelId(Long authorLevelId);

    List<AuthorLevel> selectAuthorLevelListAll();
}
