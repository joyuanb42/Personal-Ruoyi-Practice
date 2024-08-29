package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BrandInfo;

/**
 * 品牌Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface BrandInfoMapper 
{
    /**
     * 查询品牌
     * 
     * @param brandId 品牌主键
     * @return 品牌
     */
    public BrandInfo selectBrandInfoByBrandId(Long brandId);

    /**
     * 查询品牌列表
     * 
     * @param brandInfo 品牌
     * @return 品牌集合
     */
    public List<BrandInfo> selectBrandInfoList(BrandInfo brandInfo);

    /**
     * 新增品牌
     * 
     * @param brandInfo 品牌
     * @return 结果
     */
    public int insertBrandInfo(BrandInfo brandInfo);

    /**
     * 修改品牌
     * 
     * @param brandInfo 品牌
     * @return 结果
     */
    public int updateBrandInfo(BrandInfo brandInfo);

    /**
     * 删除品牌
     * 
     * @param brandId 品牌主键
     * @return 结果
     */
    public int deleteBrandInfoByBrandId(Long brandId);

    /**
     * 批量删除品牌
     * 
     * @param brandIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrandInfoByBrandIds(Long[] brandIds);

    List<BrandInfo> selectListAll();
}
