package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BrandInfoMapper;
import com.ruoyi.system.domain.BrandInfo;
import com.ruoyi.system.service.IBrandInfoService;

/**
 * 品牌Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class BrandInfoServiceImpl implements IBrandInfoService 
{
    @Autowired
    private BrandInfoMapper brandInfoMapper;

    /**
     * 查询品牌
     * 
     * @param brandId 品牌主键
     * @return 品牌
     */
    @Override
    public BrandInfo selectBrandInfoByBrandId(Long brandId)
    {
        return brandInfoMapper.selectBrandInfoByBrandId(brandId);
    }

    /**
     * 查询品牌列表
     * 
     * @param brandInfo 品牌
     * @return 品牌
     */
    @Override
    public List<BrandInfo> selectBrandInfoList(BrandInfo brandInfo)
    {
        return brandInfoMapper.selectBrandInfoList(brandInfo);
    }

    /**
     * 新增品牌
     * 
     * @param brandInfo 品牌
     * @return 结果
     */
    @Override
    public int insertBrandInfo(BrandInfo brandInfo)
    {
        return brandInfoMapper.insertBrandInfo(brandInfo);
    }

    /**
     * 修改品牌
     * 
     * @param brandInfo 品牌
     * @return 结果
     */
    @Override
    public int updateBrandInfo(BrandInfo brandInfo)
    {
        return brandInfoMapper.updateBrandInfo(brandInfo);
    }

    /**
     * 批量删除品牌
     * 
     * @param brandIds 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandInfoByBrandIds(Long[] brandIds)
    {
        return brandInfoMapper.deleteBrandInfoByBrandIds(brandIds);
    }

    /**
     * 删除品牌信息
     * 
     * @param brandId 品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandInfoByBrandId(Long brandId)
    {
        return brandInfoMapper.deleteBrandInfoByBrandId(brandId);
    }

    @Override
    public List<BrandInfo> selectBrandInfoListAll() {
        return brandInfoMapper.selectListAll();
    }
}
