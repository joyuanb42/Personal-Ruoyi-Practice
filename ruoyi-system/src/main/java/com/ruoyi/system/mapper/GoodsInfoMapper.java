package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GoodsInfo;

/**
 * 商品SPUMapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface GoodsInfoMapper 
{
    /**
     * 查询商品SPU
     * 
     * @param goodsId 商品SPU主键
     * @return 商品SPU
     */
    public GoodsInfo selectGoodsInfoByGoodsId(Long goodsId);

    /**
     * 查询商品SPU列表
     * 
     * @param goodsInfo 商品SPU
     * @return 商品SPU集合
     */
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo);

    /**
     * 新增商品SPU
     * 
     * @param goodsInfo 商品SPU
     * @return 结果
     */
    public int insertGoodsInfo(GoodsInfo goodsInfo);

    /**
     * 修改商品SPU
     * 
     * @param goodsInfo 商品SPU
     * @return 结果
     */
    public int updateGoodsInfo(GoodsInfo goodsInfo);

    /**
     * 删除商品SPU
     * 
     * @param goodsId 商品SPU主键
     * @return 结果
     */
    public int deleteGoodsInfoByGoodsId(Long goodsId);

    /**
     * 批量删除商品SPU
     * 
     * @param goodsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsInfoByGoodsIds(Long[] goodsIds);
}