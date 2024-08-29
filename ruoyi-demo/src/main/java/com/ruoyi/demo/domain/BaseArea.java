package com.ruoyi.demo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 地区对象 base_area
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
public class BaseArea extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 地区主键 */
    private Long areaId;

    /** 所属地区 */
    @Excel(name = "所属地区")
    private Long areaParId;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String areaName;

    /** 地区代号 */
    @Excel(name = "地区代号")
    private String areaCode;

    /** 区等级 */
    @Excel(name = "区等级")
    private Long areaLevel;

    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setAreaParId(Long areaParId) 
    {
        this.areaParId = areaParId;
    }

    public Long getAreaParId() 
    {
        return areaParId;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setAreaLevel(Long areaLevel) 
    {
        this.areaLevel = areaLevel;
    }

    public Long getAreaLevel() 
    {
        return areaLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("areaId", getAreaId())
            .append("areaParId", getAreaParId())
            .append("areaName", getAreaName())
            .append("areaCode", getAreaCode())
            .append("areaLevel", getAreaLevel())
            .toString();
    }
}
