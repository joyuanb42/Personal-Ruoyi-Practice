import request from '@/utils/request'

// 查询地区树形
export function listBaseAreaTree() {
  return request({
    url: '/demo/baseArea/getData',
    method: 'get',
  })
}

// 查询地区列表
export function listBaseArea(query) {
  return request({
    url: '/demo/baseArea/list',
    method: 'get',
    params: query
  })
}

// 查询地区详细
export function getBaseArea(areaId) {
  return request({
    url: '/demo/baseArea/' + areaId,
    method: 'get'
  })
}

// 新增地区
export function addBaseArea(data) {
  return request({
    url: '/demo/baseArea',
    method: 'post',
    data: data
  })
}

// 修改地区
export function updateBaseArea(data) {
  return request({
    url: '/demo/baseArea',
    method: 'put',
    data: data
  })
}

// 删除地区
export function delBaseArea(areaId) {
  return request({
    url: '/demo/baseArea/' + areaId,
    method: 'delete'
  })
}
