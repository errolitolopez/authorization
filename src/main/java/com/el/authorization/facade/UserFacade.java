package com.el.authorization.facade;

import com.el.authorization.domain.req.user.QueryUserReq;
import com.el.authorization.domain.req.user.UserReq;
import com.el.authorization.domain.req.user.ex.AddRoleUserReq;
import com.el.authorization.domain.req.user.ex.CreateUserReq;
import com.el.authorization.domain.req.user.ex.UpdatePasswordReq;
import com.el.authorization.domain.req.user.ex.UpdateUsernameReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.user.UserRsp;

public interface UserFacade {
    Response<Paged<UserRsp>> query(QueryUserReq req);

    Response<UserRsp> selectById(Long id);

    Response<Integer> deleteById(Long id);

    Response<Integer> updateById(UserReq req);

    Response<Integer> createUser(CreateUserReq req);

    Response<Integer> updateUsername(UpdateUsernameReq req);

    Response<Integer> updatePassword(UpdatePasswordReq req);

    Response<Integer> addRole(AddRoleUserReq req);
}
