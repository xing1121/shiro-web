package com.sf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.domain.UserRoles;
import com.sf.domain.UserRolesExample;

public interface UserRolesMapper {
    /**
     *
     * @mbggenerated 2018-07-18
     */
    int countByExample(UserRolesExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int deleteByExample(UserRolesExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int insert(UserRoles record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int insertSelective(UserRoles record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    List<UserRoles> selectByExample(UserRolesExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    UserRoles selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByExampleSelective(@Param("record") UserRoles record, @Param("example") UserRolesExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByExample(@Param("record") UserRoles record, @Param("example") UserRolesExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByPrimaryKeySelective(UserRoles record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByPrimaryKey(UserRoles record);
}