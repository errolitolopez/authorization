package com.el.authorization.domain.req;

public class BaseReq {
    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private String orderBy;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "BaseReq{" +
        		"pageNo=" + pageNo +
        		", pageSize=" + pageSize +
        		", orderBy='" + orderBy + '\'' +
        '}';
    }
}