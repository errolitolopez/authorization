package com.el.authorization.service;

import com.el.authorization.domain.req.sequence.QuerySequenceReq;
import com.el.authorization.domain.req.sequence.SequenceReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.sequence.SequenceRsp;

public interface SequenceService {
    Paged<SequenceRsp> query(QuerySequenceReq req);

    Integer create(SequenceReq req);

    SequenceRsp selectById(Long id);

    Integer deleteById(Long id);

    Integer updateById(SequenceReq req);

    Long getNextVal(String seqName);
}