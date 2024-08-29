import request from '@/utils/request'

// 查询品牌列表 全查
export function listBrandInfoAll() {
  return request({
    url: '/system/brandInfo/listAll',
    method: 'get',
  })
}

// 查询品牌列表
export function listBrandInfo(query) {
  return request({
    url: '/system/brandInfo/list',
    method: 'get',
    params: query
  })
}

// 查询品牌详细
export function getBrandInfo(brandId) {
  return request({
    url: '/system/brandInfo/' + brandId,
    method: 'get'
  })
}

// 新增品牌
export function addBrandInfo(data) {
  return request({
    url: '/system/brandInfo',
    method: 'post',
    data: data
  })
}

// 修改品牌
export function updateBrandInfo(data) {
  return request({
    url: '/system/brandInfo',
    method: 'put',
    data: data
  })
}

// 删除品牌
export function delBrandInfo(brandId) {
  return request({
    url: '/system/brandInfo/' + brandId,
    method: 'delete'
  })
}
