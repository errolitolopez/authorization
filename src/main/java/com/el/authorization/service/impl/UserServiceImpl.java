package com.el.authorization.service.impl;

import com.el.authorization.constant.FlagEnum;
import com.el.authorization.dao.TUser;
import com.el.authorization.dao.TUserExample;
import com.el.authorization.dao.TUserExample.Criteria;
import com.el.authorization.dao.TUserMapper;
import com.el.authorization.domain.req.user.QueryUserReq;
import com.el.authorization.domain.req.user.UserReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.user.UserRsp;
import com.el.authorization.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public Paged<UserRsp> query(QueryUserReq req) {
        Paged<UserRsp> paged = new Paged<UserRsp>();
        paged.setPageNo(req.getPageNo());
        paged.setPageSize(req.getPageSize());
        TUserExample example = new TUserExample();
        if (req.getOrderBy() != null && req.getOrderBy().trim().length() > 0) {
            example.setOrderByClause(req.getOrderBy());
        }
        TUserExample.Criteria criteria = example.createCriteria();
        buildCriteria(req, criteria);
        long total = tUserMapper.countByExample(example);
        paged.setTotal(total);
        if (total == 0) {
            return paged;
        }
        RowBounds rowBounds = new RowBounds((paged.getPageNo() - 1) * paged.getPageSize(), paged.getPageSize());
        List<TUser> userList = tUserMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<UserRsp> userRspList = new ArrayList<>();
        for (TUser tUser : userList) {
            UserRsp userRsp = new UserRsp();
            BeanUtils.copyProperties(tUser, userRsp);
            userRspList.add(userRsp);
        }
        paged.setData(userRspList);
        return paged;
    }

    @Override
    public Integer create(UserReq req) {
        TUser tUser = new TUser();
        BeanUtils.copyProperties(req, tUser);
        tUser.setCreatedBy("SYSTEM");
        tUser.setLastUpdatedBy("SYSTEM");
        tUser.setCreatedDate(new Date());
        tUser.setLastUpdatedDate(new Date());
        tUser.setFlag(FlagEnum.ENABLED.VALUE);
        return tUserMapper.insertSelective(tUser);
    }

    @Override
    public UserRsp selectById(Long id) {
        if (id == null) {
            return null;
        }
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        if (tUser == null) {
            return null;
        }
        UserRsp userRsp = new UserRsp();
        BeanUtils.copyProperties(tUser, userRsp);
        return userRsp;
    }

    @Override
    public Integer deleteById(Long id) {
        if (id == null) {
            return 0;
        }
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        if (tUser == null) {
            return 0;
        }
        return tUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateById(UserReq req) {
        if (req == null || req.getId() == null) {
            return 0;
        }
        TUser tUser = tUserMapper.selectByPrimaryKey(req.getId());
        if (tUser == null) {
            return 0;
        }
        BeanUtils.copyProperties(req, tUser);
        tUser.setLastUpdatedDate(new Date());
        tUser.setLastUpdatedBy("SYSTEM");
        return tUserMapper.updateByPrimaryKeySelective(tUser);
    }

    @Override
    public UserRsp selectByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new NullPointerException("Missing value of required parameter 'username'.");
        }
        TUserExample example = new TUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameLikeInsensitive(username);
        List<TUser> userList = tUserMapper.selectByExample(example);
        if (userList.size() > 0) {
            UserRsp userRsp = new UserRsp();
            BeanUtils.copyProperties(userList.get(0), userRsp);
            return userRsp;
        }
        return null;
    }

    private void buildCriteria(QueryUserReq req, Criteria criteria) {
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getUsername() != null && req.getUsername().trim().length() > 0) {
            criteria.andUsernameEqualTo(req.getUsername());
        }
        if (req.getPassword() != null && req.getPassword().trim().length() > 0) {
            criteria.andPasswordEqualTo(req.getPassword());
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