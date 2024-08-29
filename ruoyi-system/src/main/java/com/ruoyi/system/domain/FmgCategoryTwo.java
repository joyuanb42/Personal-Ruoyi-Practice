package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二级分类对象 fmg_category_two
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public class FmgCategoryTwo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 二级分类主键 */
    private Long ctwoId;

    /** 一级分类#foreign#fmg_category_one=cone_id */
    @Excel(name = "一级分类#foreign#fmg_category_one=cone_id")
    private Long coneId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String ctwoName;

    /** 分类序号 */
    @Excel(name = "分类序号")
    private Long ctwoNum;

    /** 分类代号 */
    @Excel(name = "分类代号")
    private String ctwoCode;

    /** 分类说明 */
    @Excel(name = "分类说明")
    private String ctwoInfo;

    /** 一级分类名称#extend#fmg_category_one=cone_name */
    @Excel(name = "一级分类名称#extend#fmg_category_one=cone_name")
    private String coneName;

    public void setCtwoId(Long ctwoId) 
    {
        this.ctwoId = ctwoId;
    }

    public Long getCtwoId() 
    {
        return ctwoId;
    }
    public void setConeId(Long coneId) 
    {
        this.coneId = coneId;
    }

    public Long getConeId() 
    {
        return coneId;
    }
    public void setCtwoName(String ctwoName) 
    {
        this.ctwoName = ctwoName;
    }

    public String getCtwoName() 
    {
        return ctwoName;
    }
    public void setCtwoNum(Long ctwoNum) 
    {
        this.ctwoNum = ctwoNum;
    }

    public Long getCtwoNum() 
    {
        return ctwoNum;
    }
    public void setCtwoCode(String ctwoCode) 
    {
        this.ctwoCode = ctwoCode;
    }

    public String getCtwoCode() 
    {
        return ctwoCode;
    }
    public void setCtwoInfo(String ctwoInfo) 
    {
        this.ctwoInfo = ctwoInfo;
    }

    public String getCtwoInfo() 
    {
        return ctwoInfo;
    }
    public void setConeName(String coneName) 
    {
        this.coneName = coneName;
    }

    public String getConeName() 
    {
        return coneName;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ctwoId", getCtwoId())
            .append("coneId", getConeId())
            .append("ctwoName", getCtwoName())
            .append("ctwoNum", getCtwoNum())
            .append("ctwoCode", getCtwoCode())
            .append("ctwoInfo", getCtwoInfo())
            .append("coneName", getConeName())
            .toString();
    }
}
