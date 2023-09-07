package com.el.authorization.facade.impl;

import com.el.authorization.domain.req.permission.PermissionReq;
import com.el.authorization.domain.req.permission.QueryPermissionReq;
import com.el.authorization.domain.req.permission.ex.CreatePermissionReq;
import com.el.authorization.domain.req.permission.ex.UpdatePermissionReq;
import com.el.authorization.domain.req.rolepermission.RolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.permission.PermissionRsp;
import com.el.authorization.domain.rsp.role.RoleRsp;
import com.el.authorization.facade.PermissionFacade;
import com.el.authorization.service.*;
import com.el.authorization.utils.ErrorUtils;
import com.el.authorization.utils.NameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class PermissionFacadeImpl implements PermissionFacade {

    @Resource
    private SequenceService sequenceService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private UserRolePermissionService userRolePermissionService;

    @Override
    public Response<Paged<PermissionRsp>> query(QueryPermissionReq req) {
        Response response = new Response();
        response.setResult(permissionService.query(req));
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Response<Integer> create(CreatePermissionReq req) {
        RoleRsp role = roleService.selectById(req.getRoleId());
        if (Objects.isNull(role)) {
            return ErrorUtils.buildErrorResponse("roleId", "The field 'roleId' did not find any roles");
        }
        String permissionCode = NameUtils.replaceSpaceToUnderScore(role.getRoleCode(), req.getPermissionCode());
        if (Objects.nonNull(permissionService.selectByPermissionCode(permissionCode))) {
            return ErrorUtils.buildErrorResponse("permissionCode", "The field 'permissionCode' is already exists");
        }

        PermissionReq permissionReq = new PermissionReq();
        Long permissionId = sequenceService.getNextVal("PERMISSION_SEQ");
        permissionReq.setId(permissionId);
        permissionReq.setPermissionCode(permissionCode);
        permissionReq.setPermissionName(permissionCode);
        permissionService.create(permissionReq);

        RolePermissionReq rolePermissionReq = new RolePermissionReq();
        rolePermissionReq.setId(sequenceService.getNextVal("ROLE_PERMISSION_SEQ"));
        rolePermissionReq.setRoleId(role.getId());
        rolePermissionReq.setPermissionId(permissionId);
        rolePermissionService.create(rolePermissionReq);

        Response response = new Response();
        response.setResult(1);
        return response;
    }

    @Override
    public Response<PermissionRsp> selectById(Long id) {
        Response response = new Response();
        response.setResult(permissionService.selectById(id));
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Response<Integer> deleteById(Long id) {
        Response response = new Response();
        permissionService.deleteById(id);
        rolePermissionService.deleteByPermissionId(id);
        userRolePermissionService.deleteByPermissionId(id);
        return response;
    }

    @Override
    public Response<Integer> updateById(UpdatePermissionReq req) {
        RoleRsp role = roleService.selectById(req.getRoleId());
        if (Objects.isNull(role)) {
            return ErrorUtils.buildErrorResponse("roleId", "The field 'roleId' did not find any roles");
        }

        PermissionRsp permission = permissionService.selectById(req.getPermissionId());
        if (Objects.isNull(permission)) {
            return ErrorUtils.buildErrorResponse("permissionId", "The field 'permissionId' did not find any permissions");
        }
        String permissionCode = NameUtils.replaceSpaceToUnderScore(role.getRoleCode(), req.getPermissionCode());
        if (!permission.getPermissionCode().equals(permissionCode)) {

            PermissionRsp permissionByPermissionCode = permissionService.selectByPermissionCode(permissionCode);
            if (Objects.nonNull(permissionByPermissionCode)) {
                return ErrorUtils.buildErrorResponse("permissionCode", "The field 'permissionCode' is already exists");
            }

            PermissionReq permissionReq = new PermissionReq();
            permissionReq.setId(req.getPermissionId());
            permissionReq.setPermissionCode(permissionCode);
            permissionReq.setPermissionName(permissionCode);
            permissionService.updateById(permissionReq);
        }

        Response response = new Response();
        response.setResult(1);
        return response;
    }
}