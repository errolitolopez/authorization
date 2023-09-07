package com.el.authorization.service.impl;

import com.el.authorization.constant.FlagEnum;
import com.el.authorization.dao.TSequence;
import com.el.authorization.dao.TSequenceExample;
import com.el.authorization.dao.TSequenceExample.Criteria;
import com.el.authorization.dao.TSequenceMapper;
import com.el.authorization.domain.req.sequence.QuerySequenceReq;
import com.el.authorization.domain.req.sequence.SequenceReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.sequence.SequenceRsp;
import com.el.authorization.service.SequenceService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Resource
    private TSequenceMapper tSequenceMapper;

    @Override
    public Paged<SequenceRsp> query(QuerySequenceReq req) {
        Paged<SequenceRsp> paged = new Paged<SequenceRsp>();
        paged.setPageNo(req.getPageNo());
        paged.setPageSize(req.getPageSize());
        TSequenceExample example = new TSequenceExample();
        if (req.getOrderBy() != null && req.getOrderBy().trim().length() > 0) {
            example.setOrderByClause(req.getOrderBy());
        }
        TSequenceExample.Criteria criteria = example.createCriteria();
        buildCriteria(req, criteria);
        long total = tSequenceMapper.countByExample(example);
        paged.setTotal(total);
        if (total == 0) {
            return paged;
        }
        RowBounds rowBounds = new RowBounds((paged.getPageNo() - 1) * paged.getPageSize(), paged.getPageSize());
        List<TSequence> sequenceList = tSequenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<SequenceRsp> sequenceRspList = new ArrayList<>();
        for (TSequence tSequence : sequenceList) {
            SequenceRsp sequenceRsp = new SequenceRsp();
            BeanUtils.copyProperties(tSequence, sequenceRsp);
            sequenceRspList.add(sequenceRsp);
        }
        paged.setData(sequenceRspList);
        return paged;
    }

    @Override
    public Integer create(SequenceReq req) {
        TSequence tSequence = new TSequence();
        BeanUtils.copyProperties(req, tSequence);
        return tSequenceMapper.insertSelective(tSequence);
    }

    @Override
    public SequenceRsp selectById(Long id) {
        if (id == null) {
            return null;
        }
        TSequence tSequence = tSequenceMapper.selectByPrimaryKey(id);
        if (tSequence == null) {
            return null;
        }
        SequenceRsp sequenceRsp = new SequenceRsp();
        BeanUtils.copyProperties(tSequence, sequenceRsp);
        return sequenceRsp;
    }

    @Override
    public Integer deleteById(Long id) {
        if (id == null) {
            return 0;
        }
        TSequence tSequence = tSequenceMapper.selectByPrimaryKey(id);
        if (tSequence == null) {
            return 0;
        }
        return tSequenceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateById(SequenceReq req) {
        if (req == null || req.getId() == null) {
            return 0;
        }
        TSequence tSequence = tSequenceMapper.selectByPrimaryKey(req.getId());
        if (tSequence == null) {
            return 0;
        }
        BeanUtils.copyProperties(req, tSequence);
        return tSequenceMapper.updateByPrimaryKeySelective(tSequence);
    }

    @Override
    public Long getNextVal(String seqName) {
        if (seqName == null) {
            return null;
        }
        TSequenceExample example = new TSequenceExample();
        Criteria criteria = example.createCriteria();
        criteria.andSeqNameEqualTo(seqName);
        List<TSequence> sequenceList = tSequenceMapper.selectByExample(example);
        if (sequenceList.size() == 0) {
            example.clear();
            example.setOrderByClause("ID DESC");
            sequenceList = tSequenceMapper.selectByExampleWithRowbounds(example, new RowBounds(0, 1));
            TSequence sequence = new TSequence();
            sequence.setId(sequenceList.size() > 0 ? sequenceList.get(0).getId() + 1 : 100_000L);
            sequence.setSeqName(seqName);
            sequence.setNextVal(100_001L);
            sequence.setCreatedBy("SYSTEM");
            sequence.setCreatedDate(new Date());
            sequence.setLastUpdatedBy("SYSTEM");
            sequence.setLastUpdatedDate(new Date());
            sequence.setFlag(FlagEnum.ENABLED.VALUE);
            tSequenceMapper.insertSelective(sequence);
            return sequence.getNextVal() - 1;
        }
        TSequence sequence = sequenceList.get(0);
        sequence.setNextVal(sequence.getNextVal() + 1);
        sequence.setLastUpdatedDate(new Date());
        tSequenceMapper.updateByPrimaryKeySelective(sequence);
        return sequence.getNextVal() - 1;
    }

    private void buildCriteria(QuerySequenceReq req, Criteria criteria) {
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getSeqName() != null && req.getSeqName().trim().length() > 0) {
            criteria.andSeqNameEqualTo(req.getSeqName());
        }
        if (req.getNextVal() != null) {
            criteria.andNextValEqualTo(req.getNextVal());
        }
        if (req.getFlag() != null) {
            criteria.andFlagEqualTo(req.getFlag());
        }
        if (req.getCreatedDate() != null) {
            criteria.andCreatedDateEqualTo(req.getCreatedDate());
        }
        if (req.getCreatedBy() != null && req.getCreatedBy().trim().length() > 0) {
            criteria.andCreatedByEqualTo(req.getCreatedBy());
        }
        if (req.getLastUpdatedDate() != null) {
            criteria.andLastUpdatedDateEqualTo(req.getLastUpdatedDate());
        }
        if (req.getLastUpdatedBy() != null && req.getLastUpdatedBy().trim().length() > 0) {
            criteria.andLastUpdatedByEqualTo(req.getLastUpdatedBy());
        }
    }
}