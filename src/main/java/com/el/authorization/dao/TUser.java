package com.el.authorization.dao;

import java.io.Serializable;
import java.util.Date;

public class TUser implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.ID
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.USERNAME
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.PASSWORD
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.FLAG
     *
     * @mbg.generated
     */
    private Short flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.CREATED_DATE
     *
     * @mbg.generated
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.CREATED_BY
     *
     * @mbg.generated
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.LAST_UPDATED_DATE
     *
     * @mbg.generated
     */
    private Date lastUpdatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.LAST_UPDATED_BY
     *
     * @mbg.generated
     */
    private String lastUpdatedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.ID
     *
     * @return the value of t_user.ID
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.ID
     *
     * @param id the value for t_user.ID
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.USERNAME
     *
     * @return the value of t_user.USERNAME
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.USERNAME
     *
     * @param username the value for t_user.USERNAME
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.PASSWORD
     *
     * @return the value of t_user.PASSWORD
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.PASSWORD
     *
     * @param password the value for t_user.PASSWORD
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.FLAG
     *
     * @return the value of t_user.FLAG
     *
     * @mbg.generated
     */
    public Short getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.FLAG
     *
     * @param flag the value for t_user.FLAG
     *
     * @mbg.generated
     */
    public void setFlag(Short flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.CREATED_DATE
     *
     * @return the value of t_user.CREATED_DATE
     *
     * @mbg.generated
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.CREATED_DATE
     *
     * @param createdDate the value for t_user.CREATED_DATE
     *
     * @mbg.generated
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.CREATED_BY
     *
     * @return the value of t_user.CREATED_BY
     *
     * @mbg.generated
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.CREATED_BY
     *
     * @param createdBy the value for t_user.CREATED_BY
     *
     * @mbg.generated
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.LAST_UPDATED_DATE
     *
     * @return the value of t_user.LAST_UPDATED_DATE
     *
     * @mbg.generated
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.LAST_UPDATED_DATE
     *
     * @param lastUpdatedDate the value for t_user.LAST_UPDATED_DATE
     *
     * @mbg.generated
     */
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.LAST_UPDATED_BY
     *
     * @return the value of t_user.LAST_UPDATED_BY
     *
     * @mbg.generated
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.LAST_UPDATED_BY
     *
     * @param lastUpdatedBy the value for t_user.LAST_UPDATED_BY
     *
     * @mbg.generated
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", flag=").append(flag);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", lastUpdatedDate=").append(lastUpdatedDate);
        sb.append(", lastUpdatedBy=").append(lastUpdatedBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}