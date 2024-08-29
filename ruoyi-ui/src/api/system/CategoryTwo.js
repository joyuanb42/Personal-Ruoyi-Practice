import request from '@/utils/request'

// 查询二级分类列表 全查
export function listCategoryTwoAll() {
  return request({
    url: '/system/CategoryTwo/listAll',
    method: 'get',
  })
}

// 查询二级分类列表
export function listCategoryTwo(query) {
  return request({
    url: '/system/CategoryTwo/list',
    method: 'get',
    params: query
  })
}

// 查询二级分类详细
export function getCategoryTwo(ctwoId) {
  return request({
    url: '/system/CategoryTwo/' + ctwoId,
    method: 'get'
  })
}

// 新增二级分类
export function addCategoryTwo(data) {
  return request({
    url: '/system/CategoryTwo',
    method: 'post',
    data: data
  })
}

// 修改二级分类
export function updateCategoryTwo(data) {
  return request({
    url: '/system/CategoryTwo',
    method: 'put',
    data: data
  })
}

// 删除二级分类
export function delCategoryTwo(ctwoId) {
  return request({
    url: '/system/CategoryTwo/' + ctwoId,
    method: 'delete'
  })
}
