package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 一级分类对象 fmg_category_one
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public class FmgCategoryOne extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 一级分类主键 */
    private Long coneId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String coneName;

    /** 分类序号 */
    @Excel(name = "分类序号")
    private Long coneNum;

    /** 分类代号 */
    @Excel(name = "分类代号")
    private String coneCode;

    /** 分类说明 */
    @Excel(name = "分类说明")
    private String coneInfo;

    /** 小图标 */
    @Excel(name = "小图标")
    private String coneLogoPath;

    /** 大图片 */
    @Excel(name = "大图片")
    private String conePicPath;

    public void setConeId(Long coneId) 
    {
        this.coneId = coneId;
    }

    public Long getConeId() 
    {
        return coneId;
    }
    public void setConeName(String coneName) 
    {
        this.coneName = coneName;
    }

    public String getConeName() 
    {
        return coneName;
    }
    public void setConeNum(Long coneNum) 
    {
        this.coneNum = coneNum;
    }

    public Long getConeNum() 
    {
        return coneNum;
    }
    public void setConeCode(String coneCode) 
    {
        this.coneCode = coneCode;
    }

    public String getConeCode() 
    {
        return coneCode;
    }
    public void setConeInfo(String coneInfo) 
    {
        this.coneInfo = coneInfo;
    }

    public String getConeInfo() 
    {
        return coneInfo;
    }
    public void setConeLogoPath(String coneLogoPath) 
    {
        this.coneLogoPath = coneLogoPath;
    }

    public String getConeLogoPath() 
    {
        return coneLogoPath;
    }
    public void setConePicPath(String conePicPath) 
    {
        this.conePicPath = conePicPath;
    }

    public String getConePicPath() 
    {
        return conePicPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("coneId", getConeId())
            .append("coneName", getConeName())
            .append("coneNum", getConeNum())
            .append("coneCode", getConeCode())
            .append("coneInfo", getConeInfo())
            .append("coneLogoPath", getConeLogoPath())
            .append("conePicPath", getConePicPath())
            .toString();
    }
}
