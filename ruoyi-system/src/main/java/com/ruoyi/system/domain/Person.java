package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人员对象 ttz_person
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class Person extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员主键 */
    private Long persId;

    /** 公司 */
    @Excel(name = "公司")
    private Long coId;

    /** 人员姓名 */
    @Excel(name = "人员姓名")
    private String persName;

    /** 人员性别 */
    @Excel(name = "人员性别")
    private Long persSex;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date persInDate;

    /** 薪水 */
    @Excel(name = "薪水")
    private Long persSalary;

    /** 备注 最多500字 */
    @Excel(name = "备注 最多500字")
    private String persRemark;

    private  String coName;

    public void setPersId(Long persId) 
    {
        this.persId = persId;
    }

    public Long getPersId() 
    {
        return persId;
    }
    public void setCoId(Long coId) 
    {
        this.coId = coId;
    }

    public Long getCoId() 
    {
        return coId;
    }
    public void setPersName(String persName) 
    {
        this.persName = persName;
    }

    public String getPersName() 
    {
        return persName;
    }
    public void setPersSex(Long persSex) 
    {
        this.persSex = persSex;
    }

    public Long getPersSex() 
    {
        return persSex;
    }
    public void setPersInDate(Date persInDate) 
    {
        this.persInDate = persInDate;
    }

    public Date getPersInDate() 
    {
        return persInDate;
    }
    public void setPersSalary(Long persSalary) 
    {
        this.persSalary = persSalary;
    }

    public Long getPersSalary() 
    {
        return persSalary;
    }
    public void setPersRemark(String persRemark) 
    {
        this.persRemark = persRemark;
    }

    public String getPersRemark() 
    {
        return persRemark;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "persId=" + persId +
                ", coId=" + coId +
                ", persName='" + persName + '\'' +
                ", persSex=" + persSex +
                ", persInDate=" + persInDate +
                ", persSalary=" + persSalary +
                ", persRemark='" + persRemark + '\'' +
                ", coName='" + coName + '\'' +
                '}';
    }
}
