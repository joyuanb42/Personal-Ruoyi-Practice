package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商对象 fmg_supplier_info
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class SupplierInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商主键 */
    private Long supplierId;

    /** 名称 */
    @Excel(name = "名称")
    private String supplierName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String supplierLinkPerson;

    /** 电话 */
    @Excel(name = "电话")
    private String supplierPhone;

    /** 序号 */
    @Excel(name = "序号")
    private Long supplierNum;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;

    /** 账号 */
    @Excel(name = "账号")
    private String bankSn;

    /** 地址 */
    @Excel(name = "地址")
    private String supplierAddress;

    /** 状态 */
    @Excel(name = "状态")
    private Long supplierState;

    /** 代号 */
    @Excel(name = "代号")
    private String supplierCode;

    /** 是否入住 */
    @Excel(name = "是否入住")
    private Long supplierIsJoin;

    /** 描述 */
    @Excel(name = "描述")
    private String supplierInfo;

    /** 成立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date supplierFoundedDate;

    /** 注册资金 */
    @Excel(name = "注册资金")
    private Long supplierRegisteredCapital;

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setSupplierLinkPerson(String supplierLinkPerson) 
    {
        this.supplierLinkPerson = supplierLinkPerson;
    }

    public String getSupplierLinkPerson() 
    {
        return supplierLinkPerson;
    }
    public void setSupplierPhone(String supplierPhone) 
    {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierPhone() 
    {
        return supplierPhone;
    }
    public void setSupplierNum(Long supplierNum) 
    {
        this.supplierNum = supplierNum;
    }

    public Long getSupplierNum() 
    {
        return supplierNum;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankSn(String bankSn) 
    {
        this.bankSn = bankSn;
    }

    public String getBankSn() 
    {
        return bankSn;
    }
    public void setSupplierAddress(String supplierAddress) 
    {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierAddress() 
    {
        return supplierAddress;
    }
    public void setSupplierState(Long supplierState) 
    {
        this.supplierState = supplierState;
    }

    public Long getSupplierState() 
    {
        return supplierState;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setSupplierIsJoin(Long supplierIsJoin) 
    {
        this.supplierIsJoin = supplierIsJoin;
    }

    public Long getSupplierIsJoin() 
    {
        return supplierIsJoin;
    }
    public void setSupplierInfo(String supplierInfo) 
    {
        this.supplierInfo = supplierInfo;
    }

    public String getSupplierInfo() 
    {
        return supplierInfo;
    }
    public void setSupplierFoundedDate(Date supplierFoundedDate) 
    {
        this.supplierFoundedDate = supplierFoundedDate;
    }

    public Date getSupplierFoundedDate() 
    {
        return supplierFoundedDate;
    }
    public void setSupplierRegisteredCapital(Long supplierRegisteredCapital) 
    {
        this.supplierRegisteredCapital = supplierRegisteredCapital;
    }

    public Long getSupplierRegisteredCapital() 
    {
        return supplierRegisteredCapital;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("supplierLinkPerson", getSupplierLinkPerson())
            .append("supplierPhone", getSupplierPhone())
            .append("supplierNum", getSupplierNum())
            .append("bankName", getBankName())
            .append("bankSn", getBankSn())
            .append("supplierAddress", getSupplierAddress())
            .append("supplierState", getSupplierState())
            .append("supplierCode", getSupplierCode())
            .append("supplierIsJoin", getSupplierIsJoin())
            .append("supplierInfo", getSupplierInfo())
            .append("supplierFoundedDate", getSupplierFoundedDate())
            .append("supplierRegisteredCapital", getSupplierRegisteredCapital())
            .toString();
    }
}
