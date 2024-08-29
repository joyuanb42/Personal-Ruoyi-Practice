import request from '@/utils/request'

// 查询商品SPU列表
export function listGoodsInfo(query) {
  return request({
    url: '/system/GoodsInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品SPU详细
export function getGoodsInfo(goodsId) {
  return request({
    url: '/system/GoodsInfo/' + goodsId,
    method: 'get'
  })
}

// 新增商品SPU
export function addGoodsInfo(data) {
  return request({
    url: '/system/GoodsInfo',
    method: 'post',
    data: data
  })
}

// 修改商品SPU
export function updateGoodsInfo(data) {
  return request({
    url: '/system/GoodsInfo',
    method: 'put',
    data: data
  })
}

// 删除商品SPU
export function delGoodsInfo(goodsId) {
  return request({
    url: '/system/GoodsInfo/' + goodsId,
    method: 'delete'
  })
}
