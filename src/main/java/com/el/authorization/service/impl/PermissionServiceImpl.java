package com.el.authorization.service.impl;

import com.el.authorization.constant.FlagEnum;
import com.el.authorization.dao.TPermission;
import com.el.authorization.dao.TPermissionExample;
import com.el.authorization.dao.TPermissionExample.Criteria;
import com.el.authorization.dao.TPermissionMapper;
import com.el.authorization.domain.req.permission.PermissionReq;
import com.el.authorization.domain.req.permission.QueryPermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.permission.PermissionRsp;
import com.el.authorization.service.PermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private TPermissionMapper tPermissionMapper;

    @Override
    public Paged<PermissionRsp> query(QueryPermissionReq req) {
        Paged<PermissionRsp> paged = new Paged<PermissionRsp>();
        paged.setPageNo(req.getPageNo());
        paged.setPageSize(req.getPageSize());
        TPermissionExample example = new TPermissionExample();
        if (req.getOrderBy() != null && req.getOrderBy().trim().length() > 0) {
            example.setOrderByClause(req.getOrderBy());
        }
        TPermissionExample.Criteria criteria = example.createCriteria();
        buildCriteria(req, criteria);
        long total = tPermissionMapper.countByExample(example);
        paged.setTotal(total);
        if (total == 0) {
            return paged;
        }
        RowBounds rowBounds = new RowBounds((paged.getPageNo() - 1) * paged.getPageSize(), paged.getPageSize());
        List<TPermission> permissionList = tPermissionMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<PermissionRsp> permissionRspList = new ArrayList<>();
        for (TPermission tPermission : permissionList) {
            PermissionRsp permissionRsp = new PermissionRsp();
            BeanUtils.copyProperties(tPermission, permissionRsp);
            permissionRspList.add(permissionRsp);
        }
        paged.setData(permissionRspList);
        return paged;
    }

    @Override
    public Integer create(PermissionReq req) {
        TPermission tPermission = new TPermission();
        BeanUtils.copyProperties(req, tPermission);
        tPermission.setCreatedBy("SYSTEM");
        tPermission.setLastUpdatedBy("SYSTEM");
        tPermission.setCreatedDate(new Date());
        tPermission.setLastUpdatedDate(new Date());
        tPermission.setFlag(FlagEnum.ENABLED.VALUE);
        return tPermissionMapper.insertSelective(tPermission);
    }

    @Override
    public PermissionRsp selectById(Long id) {
        if (id == null) {
            return null;
        }
        TPermission tPermission = tPermissionMapper.selectByPrimaryKey(id);
        if (tPermission == null) {
            return null;
        }
        PermissionRsp permissionRsp = new PermissionRsp();
        BeanUtils.copyProperties(tPermission, permissionRsp);
        return permissionRsp;
    }

    @Override
    public Integer deleteById(Long id) {
        if (id == null) {
            return 0;
        }
        TPermission tPermission = tPermissionMapper.selectByPrimaryKey(id);
        if (tPermission == null) {
            return 0;
        }
        return tPermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateById(PermissionReq req) {
        if (req == null || req.getId() == null) {
            return 0;
        }
        TPermission tPermission = tPermissionMapper.selectByPrimaryKey(req.getId());
        if (tPermission == null) {
            return 0;
        }
        BeanUtils.copyProperties(req, tPermission);
        tPermission.setLastUpdatedBy("SYSTEM");
        tPermission.setLastUpdatedDate(new Date());
        return tPermissionMapper.updateByPrimaryKeySelective(tPermission);
    }

    @Override
    public PermissionRsp selectByPermissionCode(String permissionCode) {
        if (StringUtils.isBlank(permissionCode)) {
            throw new NullPointerException("parameter permissionCode must not be null or empty");
        }
        TPermissionExample example = new TPermissionExample();
        TPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionCodeLikeInsensitive(permissionCode);
        List<TPermission> permissionList = tPermissionMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(permissionList)) {
            PermissionRsp permissionRsp = new PermissionRsp();
            BeanUtils.copyProperties(permissionList.get(0), permissionRsp);
            return permissionRsp;
        }
        return null;
    }

    @Override
    public List<PermissionRsp> selectByPermissionListIds(List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }
        TPermissionExample example = new TPermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(permissionIds);

        List<TPermission> permissionList = tPermissionMapper.selectByExample(example);
        List<PermissionRsp> permissionRspList = new ArrayList<>();
        for (TPermission tPermission : permissionList) {
            PermissionRsp permissionRsp = new PermissionRsp();
            BeanUtils.copyProperties(tPermission, permissionRsp);
            permissionRspList.add(permissionRsp);
        }
        return permissionRspList;
    }

    private void buildCriteria(QueryPermissionReq req, Criteria criteria) {
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getPermissionCode() != null && req.getPermissionCode().trim().length() > 0) {
            criteria.andPermissionCodeEqualTo(req.getPermissionCode());
        }
        if (req.getPermissionName() != null && req.getPermissionName().trim().length() > 0) {
            criteria.andPermissionNameEqualTo(req.getPermissionName());
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