import request from '@/utils/request'

// 查询应用声明列表
export function listBaseScene(query) {
  return request({
    url: '/demo/BaseScene/list',
    method: 'get',
    params: query
  })
}

// 查询应用声明详细
export function getBaseScene(sceneId) {
  return request({
    url: '/demo/BaseScene/' + sceneId,
    method: 'get'
  })
}

// 新增应用声明
export function addBaseScene(data) {
  return request({
    url: '/demo/BaseScene',
    method: 'post',
    data: data
  })
}

// 修改应用声明
export function updateBaseScene(data) {
  return request({
    url: '/demo/BaseScene',
    method: 'put',
    data: data
  })
}

// 删除应用声明
export function delBaseScene(sceneId) {
  return request({
    url: '/demo/BaseScene/' + sceneId,
    method: 'delete'
  })
}
