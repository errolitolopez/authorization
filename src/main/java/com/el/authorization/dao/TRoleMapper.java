package com.el.authorization.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    long countByExample(TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int deleteByExample(TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int insert(TRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int insertSelective(TRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    List<TRole> selectByExampleWithRowbounds(TRoleExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    List<TRole> selectByExample(TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    TRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TRole record);
}