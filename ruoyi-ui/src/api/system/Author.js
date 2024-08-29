import request from '@/utils/request'

// 查询作者列表
export function listAuthor(query) {
  return request({
    url: '/system/Author/list',
    method: 'get',
    params: query
  })
}

// 查询作者详细
export function getAuthor(authorId) {
  return request({
    url: '/system/Author/' + authorId,
    method: 'get'
  })
}

// 新增作者
export function addAuthor(data) {
  return request({
    url: '/system/Author',
    method: 'post',
    data: data
  })
}

// 修改作者
export function updateAuthor(data) {
  return request({
    url: '/system/Author',
    method: 'put',
    data: data
  })
}

// 删除作者
export function delAuthor(authorId) {
  return request({
    url: '/system/Author/' + authorId,
    method: 'delete'
  })
}
