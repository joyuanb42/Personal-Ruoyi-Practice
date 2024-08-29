import request from '@/utils/request'

// 4.查询一级分类列表 全查
export function listCategoryOneAll() {
  return request({
    url: '/system/CategoryOne/listAll',
    method: 'get',
  })
}

// 查询一级分类列表
export function listCategoryOne(query) {
  return request({
    url: '/system/CategoryOne/list',
    method: 'get',
    params: query
  })
}

// 查询一级分类详细
export function getCategoryOne(coneId) {
  return request({
    url: '/system/CategoryOne/' + coneId,
    method: 'get'
  })
}

// 新增一级分类
export function addCategoryOne(data) {
  return request({
    url: '/system/CategoryOne',
    method: 'post',
    data: data
  })
}

// 修改一级分类
export function updateCategoryOne(data) {
  return request({
    url: '/system/CategoryOne',
    method: 'put',
    data: data
  })
}

// 删除一级分类
export function delCategoryOne(coneId) {
  return request({
    url: '/system/CategoryOne/' + coneId,
    method: 'delete'
  })
}
