package com.el.authorization.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TRolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    long countByExample(TRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int deleteByExample(TRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int insert(TRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int insertSelective(TRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    List<TRolePermission> selectByExampleWithRowbounds(TRolePermissionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    List<TRolePermission> selectByExample(TRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    TRolePermission selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TRolePermission record, @Param("example") TRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TRolePermission record, @Param("example") TRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TRolePermission record);
}