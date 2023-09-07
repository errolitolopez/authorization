package com.el.authorization.service.impl;

import com.el.authorization.constant.FlagEnum;
import com.el.authorization.dao.TRole;
import com.el.authorization.dao.TRoleExample;
import com.el.authorization.dao.TRoleExample.Criteria;
import com.el.authorization.dao.TRoleMapper;
import com.el.authorization.domain.req.role.QueryRoleReq;
import com.el.authorization.domain.req.role.RoleReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.role.RoleRsp;
import com.el.authorization.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Resource
    private TRoleMapper tRoleMapper;

    @Override
    public Paged<RoleRsp> query(QueryRoleReq req) {
        Paged<RoleRsp> paged = new Paged<RoleRsp>();
        paged.setPageNo(req.getPageNo());
        paged.setPageSize(req.getPageSize());
        TRoleExample example = new TRoleExample();
        if (req.getOrderBy() != null && req.getOrderBy().trim().length() > 0) {
            example.setOrderByClause(req.getOrderBy());
        }
        TRoleExample.Criteria criteria = example.createCriteria();
        buildCriteria(req, criteria);
        long total = tRoleMapper.countByExample(example);
        paged.setTotal(total);
        if (total == 0) {
            return paged;
        }
        RowBounds rowBounds = new RowBounds((paged.getPageNo() - 1) * paged.getPageSize(), paged.getPageSize());
        List<TRole> roleList = tRoleMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<RoleRsp> roleRspList = new ArrayList<>();
        for (TRole tRole : roleList) {
            RoleRsp roleRsp = new RoleRsp();
            BeanUtils.copyProperties(tRole, roleRsp);
            roleRspList.add(roleRsp);
        }
        paged.setData(roleRspList);
        return paged;
    }

    @Override
    public Integer create(RoleReq req) {
        req.setRoleCode(req.getRoleCode().trim().replaceAll("\\s", "_").toUpperCase());
        TRole tRole = new TRole();
        BeanUtils.copyProperties(req, tRole);
        tRole.setCreatedBy("SYSTEM");
        tRole.setLastUpdatedBy("SYSTEM");
        tRole.setCreatedDate(new Date());
        tRole.setLastUpdatedDate(new Date());
        tRole.setFlag(FlagEnum.ENABLED.VALUE);
        return tRoleMapper.insertSelective(tRole);
    }

    @Override
    public RoleRsp selectById(Long id) {
        if (id == null) {
            return null;
        }
        TRole tRole = tRoleMapper.selectByPrimaryKey(id);
        if (tRole == null) {
            return null;
        }
        RoleRsp roleRsp = new RoleRsp();
        BeanUtils.copyProperties(tRole, roleRsp);
        return roleRsp;
    }

    @Override
    public Integer deleteById(Long id) {
        if (id == null) {
            return 0;
        }
        TRole tRole = tRoleMapper.selectByPrimaryKey(id);
        if (tRole == null) {
            return 0;
        }
        return tRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateById(RoleReq req) {
        if (req == null || req.getId() == null) {
            return 0;
        }
        TRole tRole = tRoleMapper.selectByPrimaryKey(req.getId());
        if (tRole == null) {
            return 0;
        }
        BeanUtils.copyProperties(req, tRole);
        tRole.setLastUpdatedBy("SYSTEM");
        tRole.setLastUpdatedDate(new Date());
        return tRoleMapper.updateByPrimaryKeySelective(tRole);
    }

    @Override
    public RoleRsp selectByRoleCode(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new NullPointerException("parameter roleCode must not be null or empty");
        }
        TRoleExample example = new TRoleExample();
        TRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleCodeLikeInsensitive(roleCode);
        List<TRole> roleList = tRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(roleList)) {
            RoleRsp roleRsp = new RoleRsp();
            BeanUtils.copyProperties(roleList.get(0), roleRsp);
            return roleRsp;
        }
        return null;
    }

    private void buildCriteria(QueryRoleReq req, Criteria criteria) {
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getRoleCode() != null && req.getRoleCode().trim().length() > 0) {
            criteria.andRoleCodeEqualTo(req.getRoleCode());
        }
        if (req.getRoleName() != null && req.getRoleName().trim().length() > 0) {
            criteria.andRoleNameEqualTo(req.getRoleName());
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