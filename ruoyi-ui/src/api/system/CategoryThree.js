import request from '@/utils/request'

// 查询三级分类列表 全查
export function listCategoryThreeAll() {
  return request({
    url: '/system/CategoryThree/listAll',
    method: 'get',
  })
}

// 查询三级分类列表
export function listCategoryThree(query) {
  return request({
    url: '/system/CategoryThree/list',
    method: 'get',
    params: query
  })
}

// 查询三级分类详细
export function getCategoryThree(cthrId) {
  return request({
    url: '/system/CategoryThree/' + cthrId,
    method: 'get'
  })
}

// 新增三级分类
export function addCategoryThree(data) {
  return request({
    url: '/system/CategoryThree',
    method: 'post',
    data: data
  })
}

// 修改三级分类
export function updateCategoryThree(data) {
  return request({
    url: '/system/CategoryThree',
    method: 'put',
    data: data
  })
}

// 删除三级分类
export function delCategoryThree(cthrId) {
  return request({
    url: '/system/CategoryThree/' + cthrId,
    method: 'delete'
  })
}
