package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Person;

/**
 * 人员Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface PersonMapper 
{
    /**
     * 查询人员
     * 
     * @param persId 人员主键
     * @return 人员
     */
    public Person selectPersonByPersId(Long persId);

    /**
     * 查询人员列表
     * 
     * @param person 人员
     * @return 人员集合
     */
    public List<Person> selectPersonList(Person person);

    /**
     * 新增人员
     * 
     * @param person 人员
     * @return 结果
     */
    public int insertPerson(Person person);

    /**
     * 修改人员
     * 
     * @param person 人员
     * @return 结果
     */
    public int updatePerson(Person person);

    /**
     * 删除人员
     * 
     * @param persId 人员主键
     * @return 结果
     */
    public int deletePersonByPersId(Long persId);

    /**
     * 批量删除人员
     * 
     * @param persIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonByPersIds(Long[] persIds);
}
