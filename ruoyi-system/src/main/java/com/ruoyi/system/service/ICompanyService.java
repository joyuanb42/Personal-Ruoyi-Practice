package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Company;

/**
 * 公司Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface ICompanyService 
{
    /**
     * 查询公司
     * 
     * @param coId 公司主键
     * @return 公司
     */
    public Company selectCompanyByCoId(Long coId);

    /**
     * 查询公司列表
     * 
     * @param company 公司
     * @return 公司集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增公司
     * 
     * @param company 公司
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改公司
     * 
     * @param company 公司
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 批量删除公司
     * 
     * @param coIds 需要删除的公司主键集合
     * @return 结果
     */
    public int deleteCompanyByCoIds(Long[] coIds);

    /**
     * 删除公司信息
     * 
     * @param coId 公司主键
     * @return 结果
     */
    public int deleteCompanyByCoId(Long coId);

    List<Company> selectCompanyListAll();
}
