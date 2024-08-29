import request from '@/utils/request'

// 查询人员列表
export function listPerson(query) {
  return request({
    url: '/system/person/list',
    method: 'get',
    params: query
  })
}

// 查询人员详细
export function getPerson(persId) {
  return request({
    url: '/system/person/' + persId,
    method: 'get'
  })
}

// 新增人员
export function addPerson(data) {
  return request({
    url: '/system/person',
    method: 'post',
    data: data
  })
}

// 修改人员
export function updatePerson(data) {
  return request({
    url: '/system/person',
    method: 'put',
    data: data
  })
}

// 删除人员
export function delPerson(persId) {
  return request({
    url: '/system/person/' + persId,
    method: 'delete'
  })
}
