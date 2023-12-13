package com.el.authorization.facade.impl;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.domain.req.permission.PermissionReq;
import com.el.authorization.domain.req.role.QueryRoleReq;
import com.el.authorization.domain.req.role.RoleReq;
import com.el.authorization.domain.req.role.ex.CreateRoleReq;
import com.el.authorization.domain.req.role.ex.UpdateRoleReq;
import com.el.authorization.domain.req.rolepermission.RolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.permission.PermissionRsp;
import com.el.authorization.domain.rsp.role.RoleRsp;
import com.el.authorization.domain.rsp.rolepermission.RolePermissionRsp;
import com.el.authorization.facade.RoleFacade;
import com.el.authorization.service.PermissionService;
import com.el.authorization.service.RolePermissionService;
import com.el.authorization.service.RoleService;
import com.el.authorization.service.SequenceService;
import com.el.authorization.utils.ErrorUtils;
import com.el.authorization.utils.NameUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleFacadeImpl implements RoleFacade {

    @Resource
    private SequenceService sequenceService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public Response<Paged<RoleRsp>> query(QueryRoleReq req) {
        Response response = new Response();
        response.setResult(roleService.query(req));
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Response<Integer> create(CreateRoleReq req) {
        if (Objects.nonNull(roleService.selectByRoleCode(req.getRoleCode()))) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR,"roleCode", "The field 'roleCode' is already exists");
        }

        RoleReq roleReq = new RoleReq();
        BeanUtils.copyProperties(req, roleReq);

        Long roleId = sequenceService.getNextVal("ROLE_SEQ");
        String roleCode = NameUtils.replaceSpaceToUnderScore(roleReq.getRoleCode());

        roleReq.setId(roleId);
        roleReq.setRoleCode(roleCode);
        roleService.create(roleReq);

        if (CollectionUtils.isNotEmpty(req.getPermissions())) {
            for (String permission : req.getPermissions()) {
                if (StringUtils.isNotBlank(permission)) {
                    PermissionReq permissionReq = new PermissionReq();
                    Long permissionId = sequenceService.getNextVal("PERMISSION_SEQ");
                    permissionReq.setId(permissionId);
                    String permissionCode = NameUtils.replaceSpaceToUnderScore(roleCode, permission);
                    permissionReq.setPermissionCode(permissionCode);
                    permissionReq.setPermissionName(permissionCode);
                    permissionService.create(permissionReq);

                    RolePermissionReq rolePermissionReq = new RolePermissionReq();
                    rolePermissionReq.setId(sequenceService.getNextVal("ROLE_PERMISSION_SEQ"));
                    rolePermissionReq.setRoleId(roleId);
                    rolePermissionReq.setPermissionId(permissionId);
                    rolePermissionService.create(rolePermissionReq);
                }
            }
        }

        Response response = new Response();
        response.setResult(1);
        return response;
    }

    @Override
    public Response<RoleRsp> selectById(Long id) {
        Response response = new Response();
        response.setResult(roleService.selectById(id));
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Response<Integer> deleteById(Long id) {
        List<RolePermissionRsp> rolePermissionRspList = rolePermissionService.selectRolePermissionListByRoleId(id);

        rolePermissionService.deleteByRoleId(id);

        for (RolePermissionRsp rolePermission : rolePermissionRspList) {
            permissionService.deleteById(rolePermission.getId());
        }

        roleService.deleteById(id);

        Response response = new Response();
        response.setResult(1);
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Response<Integer> updateById(UpdateRoleReq req) {
        RoleRsp role = roleService.selectById(req.getId());
        if (role == null) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR,"id", "The field 'id' did not find any roles");
        }

        final String currentRoleCode = role.getRoleCode();
        final String newRoleCode = NameUtils.replaceSpaceToUnderScore(req.getRoleCode());

        RoleReq roleReq = new RoleReq();
        if (!currentRoleCode.equals(newRoleCode)) {
            if (Objects.nonNull(roleService.selectByRoleCode(newRoleCode))) {
                return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR,"roleCode", "The field 'roleCode' is already exists");
            }
        }

        roleReq.setId(role.getId());
        roleReq.setRoleCode(newRoleCode);
        roleReq.setRoleName(req.getRoleName());
        roleService.updateById(roleReq);

        if (!currentRoleCode.equals(newRoleCode)) {
            List<RolePermissionRsp> rolePermissionList = rolePermissionService.selectRolePermissionListByRoleId(role.getId());
            if (CollectionUtils.isNotEmpty(rolePermissionList)) {
                List<Long> permissionIds = rolePermissionList
                        .stream()
                        .map(RolePermissionRsp::getPermissionId)
                        .collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(permissionIds)) {
                    List<PermissionRsp> permissionRspList = permissionService.selectByPermissionListIds(permissionIds);
                    for (PermissionRsp permission : permissionRspList) {
                        PermissionReq permissionReq = new PermissionReq();
                        String permissionCode = StringUtils.replaceOnce(permission.getPermissionName(), currentRoleCode, newRoleCode);
                        permissionReq.setId(permission.getId());
                        permissionReq.setPermissionCode(permissionCode);
                        permissionReq.setPermissionName(permissionCode);
                        permissionService.updateById(permissionReq);
                    }
                }
            }
        }

        Response response = new Response();
        response.setResult(1);
        return response;
    }
}