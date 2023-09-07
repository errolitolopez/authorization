package com.el.authorization.domain.req.sequence;

import com.el.authorization.domain.req.BaseReq;

import java.util.Date;

public class QuerySequenceReq extends BaseReq {
    private Long id;

    private String seqName;

    private Long nextVal;

    private Short flag;

    private Date createdDate;

    private String createdBy;

    private Date lastUpdatedDate;

    private String lastUpdatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public Long getNextVal() {
        return nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }

    public Short getFlag() {
        return flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return "QuerySequenceReq{" +
        		"id=" + id +
        		", seqName='" + seqName + '\'' +
        		", nextVal=" + nextVal +
        		", flag=" + flag +
        		", createdDate=" + createdDate +
        		", createdBy='" + createdBy + '\'' +
        		", lastUpdatedDate=" + lastUpdatedDate +
        		", lastUpdatedBy='" + lastUpdatedBy + '\'' +
        '}';
    }
}