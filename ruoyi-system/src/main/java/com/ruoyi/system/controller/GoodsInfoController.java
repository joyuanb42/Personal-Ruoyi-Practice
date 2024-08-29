package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GoodsInfo;
import com.ruoyi.system.service.IGoodsInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品SPUController
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/system/GoodsInfo")
public class GoodsInfoController extends BaseController
{
    @Autowired
    private IGoodsInfoService goodsInfoService;

    /**
     * 查询商品SPU列表
     */
    @PreAuthorize("@ss.hasPermi('system:GoodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsInfo goodsInfo)
    {
        startPage();
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品SPU列表
     */
    @PreAuthorize("@ss.hasPermi('system:GoodsInfo:export')")
    @Log(title = "商品SPU", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsInfo goodsInfo)
    {
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        ExcelUtil<GoodsInfo> util = new ExcelUtil<GoodsInfo>(GoodsInfo.class);
        util.exportExcel(response, list, "商品SPU数据");
    }

    /**
     * 获取商品SPU详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:GoodsInfo:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return success(goodsInfoService.selectGoodsInfoByGoodsId(goodsId));
    }

    /**
     * 新增商品SPU
     */
    @PreAuthorize("@ss.hasPermi('system:GoodsInfo:add')")
    @Log(title = "商品SPU", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsInfo goodsInfo)
    {
        return toAjax(goodsInfoService.insertGoodsInfo(goodsInfo));
    }

    /**
     * 修改商品SPU
     */
    @PreAuthorize("@ss.hasPermi('system:GoodsInfo:edit')")
    @Log(title = "商品SPU", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsInfo goodsInfo)
    {
        return toAjax(goodsInfoService.updateGoodsInfo(goodsInfo));
    }

    /**
     * 删除商品SPU
     */
    @PreAuthorize("@ss.hasPermi('system:GoodsInfo:remove')")
    @Log(title = "商品SPU", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(goodsInfoService.deleteGoodsInfoByGoodsIds(goodsIds));
    }
}
