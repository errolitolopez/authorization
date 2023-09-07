package com.el.authorization.service.impl;

import com.el.authorization.constant.FlagEnum;
import com.el.authorization.dao.TRolePermission;
import com.el.authorization.dao.TRolePermissionExample;
import com.el.authorization.dao.TRolePermissionExample.Criteria;
import com.el.authorization.dao.TRolePermissionMapper;
import com.el.authorization.domain.req.rolepermission.QueryRolePermissionReq;
import com.el.authorization.domain.req.rolepermission.RolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.rolepermission.RolePermissionRsp;
import com.el.authorization.service.RolePermissionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private TRolePermissionMapper tRolePermissionMapper;

    @Override
    public Paged<RolePermissionRsp> query(QueryRolePermissionReq req) {
        Paged<RolePermissionRsp> paged = new Paged<RolePermissionRsp>();
        paged.setPageNo(req.getPageNo());
        paged.setPageSize(req.getPageSize());
        TRolePermissionExample example = new TRolePermissionExample();
        if (req.getOrderBy() != null && req.getOrderBy().trim().length() > 0) {
            example.setOrderByClause(req.getOrderBy());
        }
        TRolePermissionExample.Criteria criteria = example.createCriteria();
        buildCriteria(req, criteria);
        long total = tRolePermissionMapper.countByExample(example);
        paged.setTotal(total);
        if (total == 0) {
            return paged;
        }
        RowBounds rowBounds = new RowBounds((paged.getPageNo() - 1) * paged.getPageSize(), paged.getPageSize());
        List<TRolePermission> rolePermissionList = tRolePermissionMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<RolePermissionRsp> rolePermissionRspList = new ArrayList<>();
        for (TRolePermission tRolePermission : rolePermissionList) {
            RolePermissionRsp rolePermissionRsp = new RolePermissionRsp();
            BeanUtils.copyProperties(tRolePermission, rolePermissionRsp);
            rolePermissionRspList.add(rolePermissionRsp);
        }
        paged.setData(rolePermissionRspList);
        return paged;
    }

    @Override
    public Integer create(RolePermissionReq req) {
        TRolePermission tRolePermission = new TRolePermission();
        BeanUtils.copyProperties(req, tRolePermission);
        tRolePermission.setCreatedBy("SYSTEM");
        tRolePermission.setLastUpdatedBy("SYSTEM");
        tRolePermission.setCreatedDate(new Date());
        tRolePermission.setLastUpdatedDate(new Date());
        tRolePermission.setFlag(FlagEnum.ENABLED.VALUE);
        return tRolePermissionMapper.insertSelective(tRolePermission);
    }

    @Override
    public RolePermissionRsp selectById(Long id) {
        if (id == null) {
            return null;
        }
        TRolePermission tRolePermission = tRolePermissionMapper.selectByPrimaryKey(id);
        if (tRolePermission == null) {
            return null;
        }
        RolePermissionRsp rolePermissionRsp = new RolePermissionRsp();
        BeanUtils.copyProperties(tRolePermission, rolePermissionRsp);
        return rolePermissionRsp;
    }

    @Override
    public Integer deleteById(Long id) {
        if (id == null) {
            return 0;
        }
        TRolePermission tRolePermission = tRolePermissionMapper.selectByPrimaryKey(id);
        if (tRolePermission == null) {
            return 0;
        }
        return tRolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateById(RolePermissionReq req) {
        if (req == null || req.getId() == null) {
            return 0;
        }
        TRolePermission tRolePermission = tRolePermissionMapper.selectByPrimaryKey(req.getId());
        if (tRolePermission == null) {
            return 0;
        }
        BeanUtils.copyProperties(req, tRolePermission);
        return tRolePermissionMapper.updateByPrimaryKeySelective(tRolePermission);
    }

    @Override
    public List<RolePermissionRsp> selectRolePermissionListByRoleId(Long id) {
        if (id == null) {
            return null;
        }
        TRolePermissionExample example = new TRolePermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(id);

        List<TRolePermission> rolePermissionList = tRolePermissionMapper.selectByExample(example);
        List<RolePermissionRsp> rolePermissionRspList = new ArrayList<>();
        for (TRolePermission tRolePermission : rolePermissionList) {
            RolePermissionRsp rolePermissionRsp = new RolePermissionRsp();
            BeanUtils.copyProperties(tRolePermission, rolePermissionRsp);
            rolePermissionRspList.add(rolePermissionRsp);
        }
        return rolePermissionRspList;
    }

    @Override
    public Integer deleteByPermissionId(Long id) {
        if (id == null) {
            return 0;
        }
        TRolePermissionExample example = new TRolePermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andPermissionIdEqualTo(id);

        return tRolePermissionMapper.deleteByExample(example);
    }

    @Override
    public Integer deleteByRoleId(Long id) {
        if (id == null) {
            return 0;
        }
        TRolePermissionExample example = new TRolePermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(id);

        return tRolePermissionMapper.deleteByExample(example);
    }

    private void buildCriteria(QueryRolePermissionReq req, Criteria criteria) {
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getRoleId() != null) {
            criteria.andRoleIdEqualTo(req.getRoleId());
        }
        if (req.getPermissionId() != null) {
            criteria.andPermissionIdEqualTo(req.getPermissionId());
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