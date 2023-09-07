package com.el.authorization.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TUserRolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    long countByExample(TUserRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int deleteByExample(TUserRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int insert(TUserRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int insertSelective(TUserRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    List<TUserRolePermission> selectByExampleWithRowbounds(TUserRolePermissionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    List<TUserRolePermission> selectByExample(TUserRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    TUserRolePermission selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TUserRolePermission record, @Param("example") TUserRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TUserRolePermission record, @Param("example") TUserRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TUserRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TUserRolePermission record);
}