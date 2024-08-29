package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Author;

/**
 * 作者Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface AuthorMapper 
{
    /**
     * 查询作者
     * 
     * @param authorId 作者主键
     * @return 作者
     */
    public Author selectAuthorByAuthorId(Long authorId);

    /**
     * 查询作者列表
     * 
     * @param author 作者
     * @return 作者集合
     */
    public List<Author> selectAuthorList(Author author);

    /**
     * 新增作者
     * 
     * @param author 作者
     * @return 结果
     */
    public int insertAuthor(Author author);

    /**
     * 修改作者
     * 
     * @param author 作者
     * @return 结果
     */
    public int updateAuthor(Author author);

    /**
     * 删除作者
     * 
     * @param authorId 作者主键
     * @return 结果
     */
    public int deleteAuthorByAuthorId(Long authorId);

    /**
     * 批量删除作者
     * 
     * @param authorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAuthorByAuthorIds(Long[] authorIds);
}
