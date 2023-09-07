package com.el.authorization.domain.rsp;

import java.util.ArrayList;
import java.util.List;

public class Paged<T> {
    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private Long total = 0L;

    private List<T> data = new ArrayList<>();

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Paged{" +
        		"pageNo=" + pageNo +
        		", pageSize=" + pageSize +
        		", total=" + total +
        		", data=" + data +
        '}';
    }
}