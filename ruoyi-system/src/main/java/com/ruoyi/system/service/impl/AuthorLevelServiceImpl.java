package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AuthorLevelMapper;
import com.ruoyi.system.domain.AuthorLevel;
import com.ruoyi.system.service.IAuthorLevelService;

/**
 * 作者级别Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class AuthorLevelServiceImpl implements IAuthorLevelService 
{
    @Autowired
    private AuthorLevelMapper authorLevelMapper;

    /**
     * 查询作者级别
     * 
     * @param authorLevelId 作者级别主键
     * @return 作者级别
     */
    @Override
    public AuthorLevel selectAuthorLevelByAuthorLevelId(Long authorLevelId)
    {
        return authorLevelMapper.selectAuthorLevelByAuthorLevelId(authorLevelId);
    }

    /**
     * 查询作者级别列表
     * 
     * @param authorLevel 作者级别
     * @return 作者级别
     */
    @Override
    public List<AuthorLevel> selectAuthorLevelList(AuthorLevel authorLevel)
    {
        return authorLevelMapper.selectAuthorLevelList(authorLevel);
    }

    /**
     * 新增作者级别
     * 
     * @param authorLevel 作者级别
     * @return 结果
     */
    @Override
    public int insertAuthorLevel(AuthorLevel authorLevel)
    {
        return authorLevelMapper.insertAuthorLevel(authorLevel);
    }

    /**
     * 修改作者级别
     * 
     * @param authorLevel 作者级别
     * @return 结果
     */
    @Override
    public int updateAuthorLevel(AuthorLevel authorLevel)
    {
        return authorLevelMapper.updateAuthorLevel(authorLevel);
    }

    /**
     * 批量删除作者级别
     * 
     * @param authorLevelIds 需要删除的作者级别主键
     * @return 结果
     */
    @Override
    public int deleteAuthorLevelByAuthorLevelIds(Long[] authorLevelIds)
    {
        return authorLevelMapper.deleteAuthorLevelByAuthorLevelIds(authorLevelIds);
    }

    /**
     * 删除作者级别信息
     * 
     * @param authorLevelId 作者级别主键
     * @return 结果
     */
    @Override
    public int deleteAuthorLevelByAuthorLevelId(Long authorLevelId)
    {
        return authorLevelMapper.deleteAuthorLevelByAuthorLevelId(authorLevelId);
    }

    @Override
    public List<AuthorLevel> selectAuthorLevelListAll() {
        return authorLevelMapper.selectListAll();
    }
}
