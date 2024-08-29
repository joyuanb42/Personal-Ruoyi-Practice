package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品SPU对象 fmg_goods_info
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class GoodsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品主键 */
    private Long goodsId;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private Long coneId;

    /** 二级分类 */
    @Excel(name = "二级分类")
    private Long ctwoId;

    /** 三级分类 */
    @Excel(name = "三级分类")
    private Long cthrId;

    /** 品牌 */
    @Excel(name = "品牌")
    private Long brdId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品代号 */
    @Excel(name = "商品代号")
    private String goodsCode;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date goodsInTime;

    /** 详细说明 */
    @Excel(name = "详细说明")
    private String goodsInfo;

    /** 最新修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最新修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 一级分类名称 */
    @Excel(name = "一级分类名称")
    private String coneName;

    /** 二级分类名称 */
    @Excel(name = "二级分类名称")
    private String ctwoName;

    /** 三级分类名称 */
    @Excel(name = "三级分类名称")
    private String cthrName;

    /** 品牌中文名 */
    @Excel(name = "品牌中文名")
    private String brandCnName;

    /** 是否上架 */
    @Excel(name = "是否上架")
    private Long isUp;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
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
    public void setCthrId(Long cthrId) 
    {
        this.cthrId = cthrId;
    }

    public Long getCthrId() 
    {
        return cthrId;
    }
    public void setBrdId(Long brdId) 
    {
        this.brdId = brdId;
    }

    public Long getBrdId() 
    {
        return brdId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setGoodsInTime(Date goodsInTime) 
    {
        this.goodsInTime = goodsInTime;
    }

    public Date getGoodsInTime() 
    {
        return goodsInTime;
    }
    public void setGoodsInfo(String goodsInfo) 
    {
        this.goodsInfo = goodsInfo;
    }

    public String getGoodsInfo() 
    {
        return goodsInfo;
    }
    public void setModifiedTime(Date modifiedTime) 
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime() 
    {
        return modifiedTime;
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
    public void setCthrName(String cthrName) 
    {
        this.cthrName = cthrName;
    }

    public String getCthrName() 
    {
        return cthrName;
    }
    public void setBrandCnName(String brandCnName) 
    {
        this.brandCnName = brandCnName;
    }

    public String getBrandCnName() 
    {
        return brandCnName;
    }
    public void setIsUp(Long isUp) 
    {
        this.isUp = isUp;
    }

    public Long getIsUp() 
    {
        return isUp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("coneId", getConeId())
            .append("ctwoId", getCtwoId())
            .append("cthrId", getCthrId())
            .append("brdId", getBrdId())
            .append("goodsName", getGoodsName())
            .append("goodsCode", getGoodsCode())
            .append("goodsInTime", getGoodsInTime())
            .append("goodsInfo", getGoodsInfo())
            .append("modifiedTime", getModifiedTime())
            .append("coneName", getConeName())
            .append("ctwoName", getCtwoName())
            .append("cthrName", getCthrName())
            .append("brandCnName", getBrandCnName())
            .append("isUp", getIsUp())
            .toString();
    }
}
