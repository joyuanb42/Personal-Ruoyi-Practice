package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SupplierInfoMapper;
import com.ruoyi.system.domain.SupplierInfo;
import com.ruoyi.system.service.ISupplierInfoService;

/**
 * 供应商Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class SupplierInfoServiceImpl implements ISupplierInfoService 
{
    @Autowired
    private SupplierInfoMapper supplierInfoMapper;

    /**
     * 查询供应商
     * 
     * @param supplierId 供应商主键
     * @return 供应商
     */
    @Override
    public SupplierInfo selectSupplierInfoBySupplierId(Long supplierId)
    {
        return supplierInfoMapper.selectSupplierInfoBySupplierId(supplierId);
    }

    /**
     * 查询供应商列表
     * 
     * @param supplierInfo 供应商
     * @return 供应商
     */
    @Override
    public List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo)
    {
        return supplierInfoMapper.selectSupplierInfoList(supplierInfo);
    }

    /**
     * 新增供应商
     * 
     * @param supplierInfo 供应商
     * @return 结果
     */
    @Override
    public int insertSupplierInfo(SupplierInfo supplierInfo)
    {
        return supplierInfoMapper.insertSupplierInfo(supplierInfo);
    }

    /**
     * 修改供应商
     * 
     * @param supplierInfo 供应商
     * @return 结果
     */
    @Override
    public int updateSupplierInfo(SupplierInfo supplierInfo)
    {
        return supplierInfoMapper.updateSupplierInfo(supplierInfo);
    }

    /**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的供应商主键
     * @return 结果
     */
    @Override
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds)
    {
        return supplierInfoMapper.deleteSupplierInfoBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商信息
     * 
     * @param supplierId 供应商主键
     * @return 结果
     */
    @Override
    public int deleteSupplierInfoBySupplierId(Long supplierId)
    {
        return supplierInfoMapper.deleteSupplierInfoBySupplierId(supplierId);
    }
}
