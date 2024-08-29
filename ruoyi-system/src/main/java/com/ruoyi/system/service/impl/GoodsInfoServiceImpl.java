package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GoodsInfoMapper;
import com.ruoyi.system.domain.GoodsInfo;
import com.ruoyi.system.service.IGoodsInfoService;

/**
 * 商品SPUService业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService 
{
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    /**
     * 查询商品SPU
     * 
     * @param goodsId 商品SPU主键
     * @return 商品SPU
     */
    @Override
    public GoodsInfo selectGoodsInfoByGoodsId(Long goodsId)
    {
        return goodsInfoMapper.selectGoodsInfoByGoodsId(goodsId);
    }

    /**
     * 查询商品SPU列表
     * 
     * @param goodsInfo 商品SPU
     * @return 商品SPU
     */
    @Override
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo)
    {
        return goodsInfoMapper.selectGoodsInfoList(goodsInfo);
    }

    /**
     * 新增商品SPU
     * 
     * @param goodsInfo 商品SPU
     * @return 结果
     */
    @Override
    public int insertGoodsInfo(GoodsInfo goodsInfo)
    {
        return goodsInfoMapper.insertGoodsInfo(goodsInfo);
    }

    /**
     * 修改商品SPU
     * 
     * @param goodsInfo 商品SPU
     * @return 结果
     */
    @Override
    public int updateGoodsInfo(GoodsInfo goodsInfo)
    {
        return goodsInfoMapper.updateGoodsInfo(goodsInfo);
    }

    /**
     * 批量删除商品SPU
     * 
     * @param goodsIds 需要删除的商品SPU主键
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByGoodsIds(Long[] goodsIds)
    {
        return goodsInfoMapper.deleteGoodsInfoByGoodsIds(goodsIds);
    }

    /**
     * 删除商品SPU信息
     * 
     * @param goodsId 商品SPU主键
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByGoodsId(Long goodsId)
    {
        return goodsInfoMapper.deleteGoodsInfoByGoodsId(goodsId);
    }
}
