import request from '@/utils/request'

// 查询作者级别列表 全查
export function listAuthorLevelAll() {
  return request({
    url: '/system/AuthorLevel/listAll',
    method: 'get',
  })
}

// 查询作者级别列表
export function listAuthorLevel(query) {
  return request({
    url: '/system/AuthorLevel/list',
    method: 'get',
    params: query
  })
}

// 查询作者级别详细
export function getAuthorLevel(authorLevelId) {
  return request({
    url: '/system/AuthorLevel/' + authorLevelId,
    method: 'get'
  })
}

// 新增作者级别
export function addAuthorLevel(data) {
  return request({
    url: '/system/AuthorLevel',
    method: 'post',
    data: data
  })
}

// 修改作者级别
export function updateAuthorLevel(data) {
  return request({
    url: '/system/AuthorLevel',
    method: 'put',
    data: data
  })
}

// 删除作者级别
export function delAuthorLevel(authorLevelId) {
  return request({
    url: '/system/AuthorLevel/' + authorLevelId,
    method: 'delete'
  })
}
