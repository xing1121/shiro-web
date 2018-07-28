package com.sf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.domain.RolesPermissions;
import com.sf.domain.RolesPermissionsExample;

public interface RolesPermissionsMapper {
    /**
     *
     * @mbggenerated 2018-07-18
     */
    int countByExample(RolesPermissionsExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int deleteByExample(RolesPermissionsExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int insert(RolesPermissions record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int insertSelective(RolesPermissions record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    List<RolesPermissions> selectByExample(RolesPermissionsExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    RolesPermissions selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByExampleSelective(@Param("record") RolesPermissions record, @Param("example") RolesPermissionsExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByExample(@Param("record") RolesPermissions record, @Param("example") RolesPermissionsExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByPrimaryKeySelective(RolesPermissions record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByPrimaryKey(RolesPermissions record);
}