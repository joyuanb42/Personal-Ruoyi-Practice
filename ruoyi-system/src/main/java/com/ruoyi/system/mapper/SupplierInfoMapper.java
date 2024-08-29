package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SupplierInfo;

/**
 * 供应商Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface SupplierInfoMapper 
{
    /**
     * 查询供应商
     * 
     * @param supplierId 供应商主键
     * @return 供应商
     */
    public SupplierInfo selectSupplierInfoBySupplierId(Long supplierId);

    /**
     * 查询供应商列表
     * 
     * @param supplierInfo 供应商
     * @return 供应商集合
     */
    public List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo);

    /**
     * 新增供应商
     * 
     * @param supplierInfo 供应商
     * @return 结果
     */
    public int insertSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 修改供应商
     * 
     * @param supplierInfo 供应商
     * @return 结果
     */
    public int updateSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 删除供应商
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierId(Long supplierId);

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds);
}
