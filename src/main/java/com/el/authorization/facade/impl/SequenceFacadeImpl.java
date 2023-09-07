package com.el.authorization.facade.impl;

import com.el.authorization.domain.req.sequence.QuerySequenceReq;
import com.el.authorization.domain.req.sequence.SequenceReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.sequence.SequenceRsp;
import com.el.authorization.facade.SequenceFacade;
import com.el.authorization.service.SequenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SequenceFacadeImpl implements SequenceFacade {

    @Resource
    private SequenceService sequenceService;

    @Override
    public Response<Paged<SequenceRsp>> query(QuerySequenceReq req) {
        Response response = new Response();
        response.setResult(sequenceService.query(req));
        return response;
    }

    @Override
    public Response<Integer> create(SequenceReq req) {
        Response response = new Response();
        response.setResult(sequenceService.create(req));
        return response;
    }

    @Override
    public Response<SequenceRsp> selectById(Long id) {
        Response response = new Response();
        response.setResult(sequenceService.selectById(id));
        return response;
    }

    @Override
    public Response<Integer> deleteById(Long id) {
        Response response = new Response();
        response.setResult(sequenceService.deleteById(id));
        return response;
    }

    @Override
    public Response<Integer> updateById(SequenceReq req) {
        Response response = new Response();
        response.setResult(sequenceService.updateById(req));
        return response;
    }
}