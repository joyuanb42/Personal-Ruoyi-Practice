package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AuthorLevel;

/**
 * 作者级别Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface AuthorLevelMapper 
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
     * 删除作者级别
     * 
     * @param authorLevelId 作者级别主键
     * @return 结果
     */
    public int deleteAuthorLevelByAuthorLevelId(Long authorLevelId);

    /**
     * 批量删除作者级别
     * 
     * @param authorLevelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAuthorLevelByAuthorLevelIds(Long[] authorLevelIds);

    List<AuthorLevel> selectListAll();
}
