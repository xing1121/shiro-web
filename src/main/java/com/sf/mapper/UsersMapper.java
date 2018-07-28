package com.sf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.domain.Users;
import com.sf.domain.UsersExample;

public interface UsersMapper {
    /**
     *
     * @mbggenerated 2018-07-18
     */
    int countByExample(UsersExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int deleteByExample(UsersExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int insert(Users record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int insertSelective(Users record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    List<Users> selectByExample(UsersExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    Users selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     *
     * @mbggenerated 2018-07-18
     */
    int updateByPrimaryKey(Users record);
}