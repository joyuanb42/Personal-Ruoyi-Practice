package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PersonMapper;
import com.ruoyi.system.domain.Person;
import com.ruoyi.system.service.IPersonService;

/**
 * 人员Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class PersonServiceImpl implements IPersonService 
{
    @Autowired
    private PersonMapper personMapper;

    /**
     * 查询人员
     * 
     * @param persId 人员主键
     * @return 人员
     */
    @Override
    public Person selectPersonByPersId(Long persId)
    {
        return personMapper.selectPersonByPersId(persId);
    }

    /**
     * 查询人员列表
     * 
     * @param person 人员
     * @return 人员
     */
    @Override
    public List<Person> selectPersonList(Person person)
    {
        return personMapper.selectPersonList(person);
    }

    /**
     * 新增人员
     * 
     * @param person 人员
     * @return 结果
     */
    @Override
    public int insertPerson(Person person)
    {
        return personMapper.insertPerson(person);
    }

    /**
     * 修改人员
     * 
     * @param person 人员
     * @return 结果
     */
    @Override
    public int updatePerson(Person person)
    {
        return personMapper.updatePerson(person);
    }

    /**
     * 批量删除人员
     * 
     * @param persIds 需要删除的人员主键
     * @return 结果
     */
    @Override
    public int deletePersonByPersIds(Long[] persIds)
    {
        return personMapper.deletePersonByPersIds(persIds);
    }

    /**
     * 删除人员信息
     * 
     * @param persId 人员主键
     * @return 结果
     */
    @Override
    public int deletePersonByPersId(Long persId)
    {
        return personMapper.deletePersonByPersId(persId);
    }
}
