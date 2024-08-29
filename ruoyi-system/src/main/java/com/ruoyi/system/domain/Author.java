package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作者对象 tth_author
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class Author extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作者主键 */
    private Long authorId;

    /** 作者级别 */
    @Excel(name = "作者级别")
    private Long authorLevelId;

    /** 作者姓名 */
    @Excel(name = "作者姓名")
    private String authorName;

    /** 作者性别 */
    @Excel(name = "作者性别")
    private Long authorSex;

    /** 作者生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "作者生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date authorBirth;

    /** 作品数量 */
    @Excel(name = "作品数量")
    private Long authorWorksCount;

    /** 作者介绍   最多500字 */
    @Excel(name = "作者介绍   最多500字")
    private String authorInfo;

    /** 作者级别对象 */
    private String authorLevelName;

    public void setAuthorId(Long authorId) 
    {
        this.authorId = authorId;
    }

    public Long getAuthorId() 
    {
        return authorId;
    }
    public void setAuthorLevelId(Long authorLevelId) 
    {
        this.authorLevelId = authorLevelId;
    }

    public Long getAuthorLevelId() 
    {
        return authorLevelId;
    }
    public void setAuthorName(String authorName) 
    {
        this.authorName = authorName;
    }

    public String getAuthorName() 
    {
        return authorName;
    }
    public void setAuthorSex(Long authorSex) 
    {
        this.authorSex = authorSex;
    }

    public Long getAuthorSex() 
    {
        return authorSex;
    }
    public void setAuthorBirth(Date authorBirth) 
    {
        this.authorBirth = authorBirth;
    }

    public Date getAuthorBirth() 
    {
        return authorBirth;
    }
    public void setAuthorWorksCount(Long authorWorksCount) 
    {
        this.authorWorksCount = authorWorksCount;
    }

    public Long getAuthorWorksCount() 
    {
        return authorWorksCount;
    }
    public void setAuthorInfo(String authorInfo) 
    {
        this.authorInfo = authorInfo;
    }

    public String getAuthorInfo() 
    {
        return authorInfo;
    }

    public String getAuthorLevelName() {
        return authorLevelName;
    }

    public void setAuthorLevelName(String authorLevelName) {
        this.authorLevelName = authorLevelName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorLevelId=" + authorLevelId +
                ", authorName='" + authorName + '\'' +
                ", authorSex=" + authorSex +
                ", authorBirth=" + authorBirth +
                ", authorWorksCount=" + authorWorksCount +
                ", authorInfo='" + authorInfo + '\'' +
                ", authorLevelName='" + authorLevelName + '\'' +
                '}';
    }
}
