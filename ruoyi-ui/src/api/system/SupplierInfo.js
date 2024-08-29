import request from '@/utils/request'

// 查询供应商列表
export function listSupplierInfo(query) {
  return request({
    url: '/system/SupplierInfo/list',
    method: 'get',
    params: query
  })
}

// 查询供应商详细
export function getSupplierInfo(supplierId) {
  return request({
    url: '/system/SupplierInfo/' + supplierId,
    method: 'get'
  })
}

// 新增供应商
export function addSupplierInfo(data) {
  return request({
    url: '/system/SupplierInfo',
    method: 'post',
    data: data
  })
}

// 修改供应商
export function updateSupplierInfo(data) {
  return request({
    url: '/system/SupplierInfo',
    method: 'put',
    data: data
  })
}

// 删除供应商
export function delSupplierInfo(supplierId) {
  return request({
    url: '/system/SupplierInfo/' + supplierId,
    method: 'delete'
  })
}
