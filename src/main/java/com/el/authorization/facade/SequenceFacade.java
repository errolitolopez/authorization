package com.el.authorization.facade;

import com.el.authorization.domain.req.sequence.QuerySequenceReq;
import com.el.authorization.domain.req.sequence.SequenceReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.sequence.SequenceRsp;

public interface SequenceFacade {
    Response<Paged<SequenceRsp>> query(QuerySequenceReq req);

    Response<Integer> create(SequenceReq req);

    Response<SequenceRsp> selectById(Long id);

    Response<Integer> deleteById(Long id);

    Response<Integer> updateById(SequenceReq req);
}