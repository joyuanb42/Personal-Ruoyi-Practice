package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 品牌对象 fmg_brand_info
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class BrandInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品牌主键 */
    private Long brandId;

    /** 中文名 */
    @Excel(name = "中文名")
    private String brandCnName;

    /** 英文名 */
    @Excel(name = "英文名")
    private String brandEnName;

    /** logo路径 */
    @Excel(name = "logo路径")
    private String brandLogoPath;

    /** 网址 */
    @Excel(name = "网址")
    private String brandWebUrl;

    /** 权重 */
    @Excel(name = "权重")
    private Long brandNum;

    /** 说明 */
    @Excel(name = "说明")
    private String brandInfo;

    /** 成立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date brandFoundedDate;

    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setBrandCnName(String brandCnName) 
    {
        this.brandCnName = brandCnName;
    }

    public String getBrandCnName() 
    {
        return brandCnName;
    }
    public void setBrandEnName(String brandEnName) 
    {
        this.brandEnName = brandEnName;
    }

    public String getBrandEnName() 
    {
        return brandEnName;
    }
    public void setBrandLogoPath(String brandLogoPath) 
    {
        this.brandLogoPath = brandLogoPath;
    }

    public String getBrandLogoPath() 
    {
        return brandLogoPath;
    }
    public void setBrandWebUrl(String brandWebUrl) 
    {
        this.brandWebUrl = brandWebUrl;
    }

    public String getBrandWebUrl() 
    {
        return brandWebUrl;
    }
    public void setBrandNum(Long brandNum) 
    {
        this.brandNum = brandNum;
    }

    public Long getBrandNum() 
    {
        return brandNum;
    }
    public void setBrandInfo(String brandInfo) 
    {
        this.brandInfo = brandInfo;
    }

    public String getBrandInfo() 
    {
        return brandInfo;
    }
    public void setBrandFoundedDate(Date brandFoundedDate) 
    {
        this.brandFoundedDate = brandFoundedDate;
    }

    public Date getBrandFoundedDate() 
    {
        return brandFoundedDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brandId", getBrandId())
            .append("brandCnName", getBrandCnName())
            .append("brandEnName", getBrandEnName())
            .append("brandLogoPath", getBrandLogoPath())
            .append("brandWebUrl", getBrandWebUrl())
            .append("brandNum", getBrandNum())
            .append("brandInfo", getBrandInfo())
            .append("brandFoundedDate", getBrandFoundedDate())
            .toString();
    }
}
