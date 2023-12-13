package com.el.authorization.facade.impl;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.domain.req.user.QueryUserReq;
import com.el.authorization.domain.req.user.UserReq;
import com.el.authorization.domain.req.user.ex.*;
import com.el.authorization.domain.req.userrolepermission.UserRolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.user.UserRsp;
import com.el.authorization.facade.UserFacade;
import com.el.authorization.service.*;
import com.el.authorization.utils.ErrorUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserFacadeImpl implements UserFacade {

    @Resource
    private SequenceService sequenceService;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserRolePermissionService userRolePermissionService;

    @Override
    public Response<Paged<UserRsp>> query(QueryUserReq req) {
        Response response = new Response();
        response.setResult(userService.query(req));
        return response;
    }

    @Override
    public Response<UserRsp> selectById(Long id) {
        Response response = new Response();
        response.setResult(userService.selectById(id));
        return response;
    }

    @Override
    public Response<Integer> deleteById(Long id) {
        Response response = new Response();
        response.setResult(userService.deleteById(id));
        return response;
    }

    @Override
    public Response<Integer> updateById(UserReq req) {
        Response response = new Response();
        response.setResult(userService.updateById(req));
        return response;
    }

    @Override
    public Response<Integer> createUser(CreateUserReq req) {
        if (userService.selectByUsername(req.getUsername()) != null) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, "username", "The field 'username' is already exists");
        }

        UserReq userReq = new UserReq();
        Long userId = sequenceService.getNextVal("USER_SEQ");
        userReq.setId(userId);
        userReq.setUsername(req.getUsername());
        userReq.setPassword(req.getPassword());
        userService.create(userReq);

        if (CollectionUtils.isNotEmpty(req.getRoles())) {
            for (RoleIdPermissionIdsReq role : req.getRoles()) {
                Long roleId = role.getRoleId();
                if (Objects.isNull(roleService.selectById(roleId))) {
                    continue;
                }
                for (Long permissionId : role.getPermissionIds()) {
                    if (Objects.isNull(permissionService.selectById(permissionId))) {
                        continue;
                    }
                    UserRolePermissionReq userRolePermissionReq = new UserRolePermissionReq();
                    userRolePermissionReq.setId(sequenceService.getNextVal("USER_ROLE_PERMISSION_REQ"));
                    userRolePermissionReq.setUserId(userId);
                    userRolePermissionReq.setRoleId(roleId);
                    userRolePermissionReq.setPermissionId(permissionId);
                    userRolePermissionService.create(userRolePermissionReq);
                }
            }
        }

        Response response = new Response();
        response.setResult(1);
        return response;
    }

    @Override
    public Response<Integer> updateUsername(UpdateUsernameReq req) {
        UserRsp user = userService.selectById(req.getId());
        if (user == null) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, "id", "The field 'id' did not find any users");
        }

        if (!user.getPassword().equalsIgnoreCase(req.getPassword())) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR,"password", "The field 'password' is incorrect");
        }

        Response response = new Response();
        if (user.getUsername().equalsIgnoreCase(req.getUsername())) {
            return response; // skip no changes
        }

        if (userService.selectByUsername(req.getUsername()) != null) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR,"username", "The field 'username' is already exists");
        }

        UserReq userReq = new UserReq();
        userReq.setId(user.getId());
        userReq.setUsername(req.getUsername());
        response.setResult(userService.updateById(userReq));
        return response;
    }

    @Override
    public Response<Integer> updatePassword(UpdatePasswordReq req) {
        UserRsp user = userService.selectById(req.getId());
        if (user == null) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, "id", "The field 'id' did not find any users");
        }

        List<Object> errors = new ArrayList<>();
        if (!user.getPassword().equalsIgnoreCase(req.getPassword())) {
            errors.add(ErrorUtils.createError("password", "The field 'password' is invalid"));
        }
        if (!req.getConfirmPassword().equalsIgnoreCase(req.getNewPassword())) {
            errors.add(ErrorUtils.createError("confirmPassword", "The field 'confirmPassword' does not match"));
        }
        if (CollectionUtils.isNotEmpty(errors)) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, errors);
        }

        Response response = new Response();
        if (user.getPassword().equalsIgnoreCase(req.getNewPassword())) {
            return response; // skip no changes
        }
        UserReq userReq = new UserReq();
        userReq.setId(user.getId());
        userReq.setPassword(req.getNewPassword());
        response.setResult(userService.updateById(userReq));
        return response;
    }

    @Override
    public Response<Integer> addRole(AddRoleUserReq req) {
        UserRsp user = userService.selectById(req.getId());
        if (user == null) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, "id", "The field 'id' did not find any users");
        }
        if (CollectionUtils.isNotEmpty(req.getRoles())) {
            for (RoleIdPermissionIdsReq role : req.getRoles()) {
                Long roleId = role.getRoleId();
                if (Objects.isNull(roleService.selectById(roleId))) {
                    continue;
                }
                for (Long permissionId : role.getPermissionIds()) {
                    if (Objects.isNull(permissionService.selectById(permissionId))) {
                        continue;
                    }
                    if (Objects.isNull(userRolePermissionService.selectUserRolePermissionByUserIdRoleIdAndPermissionId(user.getId(), roleId, permissionId))) {
                        UserRolePermissionReq userRolePermissionReq = new UserRolePermissionReq();
                        userRolePermissionReq.setId(sequenceService.getNextVal("USER_ROLE_PERMISSION_REQ"));
                        userRolePermissionReq.setUserId(user.getId());
                        userRolePermissionReq.setRoleId(roleId);
                        userRolePermissionReq.setPermissionId(permissionId);
                        userRolePermissionService.create(userRolePermissionReq);
                    }
                }
            }
        }
        Response response = new Response();
        response.setResult(1);
        return response;
    }
}
