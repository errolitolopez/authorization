package com.el.authorization.service;

import com.el.authorization.domain.req.user.QueryUserReq;
import com.el.authorization.domain.req.user.UserReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.user.UserRsp;

public interface UserService {
    Paged<UserRsp> query(QueryUserReq req);

    Integer create(UserReq req);

    UserRsp selectById(Long id);

    Integer deleteById(Long id);

    Integer updateById(UserReq req);

    UserRsp selectByUsername(String username);
}