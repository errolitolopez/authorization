package com.el.authorization.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TSequenceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    long countByExample(TSequenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int deleteByExample(TSequenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int insert(TSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int insertSelective(TSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    List<TSequence> selectByExampleWithRowbounds(TSequenceExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    List<TSequence> selectByExample(TSequenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    TSequence selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TSequence record, @Param("example") TSequenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TSequence record, @Param("example") TSequenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sequence
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TSequence record);
}