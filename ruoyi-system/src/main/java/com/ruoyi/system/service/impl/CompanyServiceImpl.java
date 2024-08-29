package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompanyMapper;
import com.ruoyi.system.domain.Company;
import com.ruoyi.system.service.ICompanyService;

/**
 * 公司Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class CompanyServiceImpl implements ICompanyService 
{
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 查询公司
     * 
     * @param coId 公司主键
     * @return 公司
     */
    @Override
    public Company selectCompanyByCoId(Long coId)
    {
        return companyMapper.selectCompanyByCoId(coId);
    }

    /**
     * 查询公司列表
     * 
     * @param company 公司
     * @return 公司
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增公司
     * 
     * @param company 公司
     * @return 结果
     */
    @Override
    public int insertCompany(Company company)
    {
        return companyMapper.insertCompany(company);
    }

    /**
     * 修改公司
     * 
     * @param company 公司
     * @return 结果
     */
    @Override
    public int updateCompany(Company company)
    {
        return companyMapper.updateCompany(company);
    }

    /**
     * 批量删除公司
     * 
     * @param coIds 需要删除的公司主键
     * @return 结果
     */
    @Override
    public int deleteCompanyByCoIds(Long[] coIds)
    {
        return companyMapper.deleteCompanyByCoIds(coIds);
    }

    /**
     * 删除公司信息
     * 
     * @param coId 公司主键
     * @return 结果
     */
    @Override
    public int deleteCompanyByCoId(Long coId)
    {
        return companyMapper.deleteCompanyByCoId(coId);
    }

    @Override
    public List<Company> selectCompanyListAll() {
        return companyMapper.selectListAll();
    }
}
