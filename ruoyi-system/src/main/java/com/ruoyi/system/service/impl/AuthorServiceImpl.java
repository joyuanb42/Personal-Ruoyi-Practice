package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AuthorMapper;
import com.ruoyi.system.domain.Author;
import com.ruoyi.system.service.IAuthorService;

/**
 * 作者Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class AuthorServiceImpl implements IAuthorService 
{
    @Autowired
    private AuthorMapper authorMapper;

    /**
     * 查询作者
     * 
     * @param authorId 作者主键
     * @return 作者
     */
    @Override
    public Author selectAuthorByAuthorId(Long authorId)
    {
        return authorMapper.selectAuthorByAuthorId(authorId);
    }

    /**
     * 查询作者列表
     * 
     * @param author 作者
     * @return 作者
     */
    @Override
    public List<Author> selectAuthorList(Author author)
    {
        return authorMapper.selectAuthorList(author);
    }

    /**
     * 新增作者
     * 
     * @param author 作者
     * @return 结果
     */
    @Override
    public int insertAuthor(Author author)
    {
        return authorMapper.insertAuthor(author);
    }

    /**
     * 修改作者
     * 
     * @param author 作者
     * @return 结果
     */
    @Override
    public int updateAuthor(Author author)
    {
        return authorMapper.updateAuthor(author);
    }

    /**
     * 批量删除作者
     * 
     * @param authorIds 需要删除的作者主键
     * @return 结果
     */
    @Override
    public int deleteAuthorByAuthorIds(Long[] authorIds)
    {
        return authorMapper.deleteAuthorByAuthorIds(authorIds);
    }

    /**
     * 删除作者信息
     * 
     * @param authorId 作者主键
     * @return 结果
     */
    @Override
    public int deleteAuthorByAuthorId(Long authorId)
    {
        return authorMapper.deleteAuthorByAuthorId(authorId);
    }
}
