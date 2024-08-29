package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 三级分类对象 fmg_category_three
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class CategoryThree extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 三级分类主键 */
    private Long cthrId;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private Long coneId;

    /** 二级分类 */
    @Excel(name = "二级分类")
    private Long ctwoId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String cthrName;

    /** 分类序号 */
    @Excel(name = "分类序号")
    private Long cthrNum;

    /** 分类代号 */
    @Excel(name = "分类代号")
    private String cthrCode;

    /** 分类说明 */
    @Excel(name = "分类说明")
    private String cthrInfo;

    /** 一级分类名称 */
    @Excel(name = "一级分类名称")
    private String coneName;

    /** 二级分类名称 */
    @Excel(name = "二级分类名称")
    private String ctwoName;

    public void setCthrId(Long cthrId) 
    {
        this.cthrId = cthrId;
    }

    public Long getCthrId() 
    {
        return cthrId;
    }
    public void setConeId(Long coneId) 
    {
        this.coneId = coneId;
    }

    public Long getConeId() 
    {
        return coneId;
    }
    public void setCtwoId(Long ctwoId) 
    {
        this.ctwoId = ctwoId;
    }

    public Long getCtwoId() 
    {
        return ctwoId;
    }
    public void setCthrName(String cthrName) 
    {
        this.cthrName = cthrName;
    }

    public String getCthrName() 
    {
        return cthrName;
    }
    public void setCthrNum(Long cthrNum) 
    {
        this.cthrNum = cthrNum;
    }

    public Long getCthrNum() 
    {
        return cthrNum;
    }
    public void setCthrCode(String cthrCode) 
    {
        this.cthrCode = cthrCode;
    }

    public String getCthrCode() 
    {
        return cthrCode;
    }
    public void setCthrInfo(String cthrInfo) 
    {
        this.cthrInfo = cthrInfo;
    }

    public String getCthrInfo() 
    {
        return cthrInfo;
    }
    public void setConeName(String coneName) 
    {
        this.coneName = coneName;
    }

    public String getConeName() 
    {
        return coneName;
    }
    public void setCtwoName(String ctwoName) 
    {
        this.ctwoName = ctwoName;
    }

    public String getCtwoName() 
    {
        return ctwoName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cthrId", getCthrId())
            .append("coneId", getConeId())
            .append("ctwoId", getCtwoId())
            .append("cthrName", getCthrName())
            .append("cthrNum", getCthrNum())
            .append("cthrCode", getCthrCode())
            .append("cthrInfo", getCthrInfo())
            .append("coneName", getConeName())
            .append("ctwoName", getCtwoName())
            .toString();
    }
}
