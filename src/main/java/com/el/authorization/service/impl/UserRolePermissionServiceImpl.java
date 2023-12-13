package com.el.authorization.service.impl;

import com.el.authorization.constant.FlagEnum;
import com.el.authorization.dao.TUserRolePermission;
import com.el.authorization.dao.TUserRolePermissionExample;
import com.el.authorization.dao.TUserRolePermissionExample.Criteria;
import com.el.authorization.dao.TUserRolePermissionMapper;
import com.el.authorization.domain.req.userrolepermission.QueryUserRolePermissionReq;
import com.el.authorization.domain.req.userrolepermission.UserRolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.userrolepermission.UserRolePermissionRsp;
import com.el.authorization.service.UserRolePermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserRolePermissionServiceImpl implements UserRolePermissionService {

    @Resource
    private TUserRolePermissionMapper tUserRolePermissionMapper;

    @Override
    public Paged<UserRolePermissionRsp> query(QueryUserRolePermissionReq req) {
        Paged<UserRolePermissionRsp> paged = new Paged<UserRolePermissionRsp>();
        paged.setPageNo(req.getPageNo());
        paged.setPageSize(req.getPageSize());
        TUserRolePermissionExample example = new TUserRolePermissionExample();
        if (req.getOrderBy() != null && req.getOrderBy().trim().length() > 0) {
            example.setOrderByClause(req.getOrderBy());
        }
        TUserRolePermissionExample.Criteria criteria = example.createCriteria();
        buildCriteria(req, criteria);
        long total = tUserRolePermissionMapper.countByExample(example);
        paged.setTotal(total);
        if (total == 0) {
            return paged;
        }
        RowBounds rowBounds = new RowBounds((paged.getPageNo() - 1) * paged.getPageSize(), paged.getPageSize());
        List<TUserRolePermission> userRolePermissionList = tUserRolePermissionMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<UserRolePermissionRsp> userRolePermissionRspList = new ArrayList<>();
        for (TUserRolePermission tUserRolePermission : userRolePermissionList) {
            UserRolePermissionRsp userRolePermissionRsp = new UserRolePermissionRsp();
            BeanUtils.copyProperties(tUserRolePermission, userRolePermissionRsp);
            userRolePermissionRspList.add(userRolePermissionRsp);
        }
        paged.setData(userRolePermissionRspList);
        return paged;
    }

    @Override
    public Integer create(UserRolePermissionReq req) {
        TUserRolePermission tUserRolePermission = new TUserRolePermission();
        BeanUtils.copyProperties(req, tUserRolePermission);
        tUserRolePermission.setCreatedBy("SYSTEM");
        tUserRolePermission.setLastUpdatedBy("SYSTEM");
        tUserRolePermission.setCreatedDate(new Date());
        tUserRolePermission.setLastUpdatedDate(new Date());
        tUserRolePermission.setFlag(FlagEnum.ENABLED.VALUE);
        return tUserRolePermissionMapper.insertSelective(tUserRolePermission);
    }

    @Override
    public UserRolePermissionRsp selectById(Long id) {
        if (id == null) {
            return null;
        }
        TUserRolePermission tUserRolePermission = tUserRolePermissionMapper.selectByPrimaryKey(id);
        if (tUserRolePermission == null) {
            return null;
        }
        UserRolePermissionRsp userRolePermissionRsp = new UserRolePermissionRsp();
        BeanUtils.copyProperties(tUserRolePermission, userRolePermissionRsp);
        return userRolePermissionRsp;
    }

    @Override
    public Integer deleteById(Long id) {
        if (id == null) {
            return 0;
        }
        TUserRolePermission tUserRolePermission = tUserRolePermissionMapper.selectByPrimaryKey(id);
        if (tUserRolePermission == null) {
            return 0;
        }
        return tUserRolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateById(UserRolePermissionReq req) {
        if (req == null || req.getId() == null) {
            return 0;
        }
        TUserRolePermission tUserRolePermission = tUserRolePermissionMapper.selectByPrimaryKey(req.getId());
        if (tUserRolePermission == null) {
            return 0;
        }
        BeanUtils.copyProperties(req, tUserRolePermission);
        return tUserRolePermissionMapper.updateByPrimaryKeySelective(tUserRolePermission);
    }

    @Override
    public Integer deleteByPermissionId(Long id) {
        if (id == null) {
            return null;
        }
        TUserRolePermissionExample example = new TUserRolePermissionExample();
        TUserRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionIdEqualTo(id);

        return tUserRolePermissionMapper.deleteByExample(example);
    }

    @Override
    public List<UserRolePermissionRsp> selectUserRolePermissionListByUserId(Long id) {
        if (id == null) {
            return new ArrayList<>();
        }
        TUserRolePermissionExample example = new TUserRolePermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);

        List<TUserRolePermission> userRolePermissionList = tUserRolePermissionMapper.selectByExample(example);
        List<UserRolePermissionRsp> userRolePermissionRspList = new ArrayList<>();
        for (TUserRolePermission tUserRolePermission : userRolePermissionList) {
            UserRolePermissionRsp userRolePermissionRsp = new UserRolePermissionRsp();
            BeanUtils.copyProperties(tUserRolePermission, userRolePermissionRsp);
            userRolePermissionRspList.add(userRolePermissionRsp);
        }
        return userRolePermissionRspList;
    }

    @Override
    public UserRolePermissionRsp selectUserRolePermissionByUserIdRoleIdAndPermissionId(Long userId, Long roleId, Long permissionId) {
        if (ObjectUtils.anyNull(userId, roleId, permissionId)) {
            return null;
        }
        TUserRolePermissionExample example = new TUserRolePermissionExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andRoleIdEqualTo(roleId);
        criteria.andPermissionIdEqualTo(permissionId);

        List<TUserRolePermission> userRolePermissionList = tUserRolePermissionMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userRolePermissionList)) {
            UserRolePermissionRsp userRolePermissionRsp = new UserRolePermissionRsp();
            BeanUtils.copyProperties(userRolePermissionList.get(0), userRolePermissionRsp);
            return userRolePermissionRsp;
        }
        return null;
    }

    private void buildCriteria(QueryUserRolePermissionReq req, Criteria criteria) {
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getUserId() != null) {
            criteria.andUserIdEqualTo(req.getUserId());
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