package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作者级别对象 tth_author_level
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class AuthorLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作者级别主键 */
    private Long authorLevelId;

    /** 作者级别名称 */
    @Excel(name = "作者级别名称")
    private String authorLevelName;

    /** 作者级别代号 */
    @Excel(name = "作者级别代号")
    private String authorLevelCode;

    public void setAuthorLevelId(Long authorLevelId) 
    {
        this.authorLevelId = authorLevelId;
    }

    public Long getAuthorLevelId() 
    {
        return authorLevelId;
    }
    public void setAuthorLevelName(String authorLevelName) 
    {
        this.authorLevelName = authorLevelName;
    }

    public String getAuthorLevelName() 
    {
        return authorLevelName;
    }
    public void setAuthorLevelCode(String authorLevelCode) 
    {
        this.authorLevelCode = authorLevelCode;
    }

    public String getAuthorLevelCode() 
    {
        return authorLevelCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("authorLevelId", getAuthorLevelId())
            .append("authorLevelName", getAuthorLevelName())
            .append("authorLevelCode", getAuthorLevelCode())
            .toString();
    }
}
