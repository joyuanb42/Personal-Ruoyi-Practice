package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司对象 ttz_company
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class Company extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司主键 */
    private Long coId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String coName;

    /** 公司代码 */
    @Excel(name = "公司代码")
    private String coCode;

    public void setCoId(Long coId) 
    {
        this.coId = coId;
    }

    public Long getCoId() 
    {
        return coId;
    }
    public void setCoName(String coName) 
    {
        this.coName = coName;
    }

    public String getCoName() 
    {
        return coName;
    }
    public void setCoCode(String coCode) 
    {
        this.coCode = coCode;
    }

    public String getCoCode() 
    {
        return coCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("coId", getCoId())
            .append("coName", getCoName())
            .append("coCode", getCoCode())
            .toString();
    }
}
