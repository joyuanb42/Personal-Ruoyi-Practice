import request from '@/utils/request'

// 查询全部公司列表
export function listCompanyAll() {
  return request({
    url: '/system/Company/listAll',
    method: 'get',
  })
}

// 查询公司列表
export function listCompany(query) {
  return request({
    url: '/system/Company/list',
    method: 'get',
    params: query
  })
}

// 查询公司详细
export function getCompany(coId) {
  return request({
    url: '/system/Company/' + coId,
    method: 'get'
  })
}

// 新增公司
export function addCompany(data) {
  return request({
    url: '/system/Company',
    method: 'post',
    data: data
  })
}

// 修改公司
export function updateCompany(data) {
  return request({
    url: '/system/Company',
    method: 'put',
    data: data
  })
}

// 删除公司
export function delCompany(coId) {
  return request({
    url: '/system/Company/' + coId,
    method: 'delete'
  })
}
