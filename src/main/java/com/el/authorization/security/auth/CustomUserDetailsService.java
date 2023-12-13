package com.el.authorization.security.auth;

import com.el.authorization.domain.rsp.permission.PermissionRsp;
import com.el.authorization.domain.rsp.role.RoleRsp;
import com.el.authorization.domain.rsp.user.UserRsp;
import com.el.authorization.domain.rsp.userrolepermission.UserRolePermissionRsp;
import com.el.authorization.service.PermissionService;
import com.el.authorization.service.RoleService;
import com.el.authorization.service.UserRolePermissionService;
import com.el.authorization.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRolePermissionService userRolePermissionService;

    @Resource
    private PermissionService permissionService;

    public UserDetails selectUserDetailsByUsername(String username) {
        UserRsp user = userService.selectByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("The field 'username' did not find any users");
        }

        List<UserRolePermissionRsp> userRolePermissionList = userRolePermissionService
                .selectUserRolePermissionListByUserId(user.getId());

        List<Long> roleIds = userRolePermissionList
                .stream()
                .map(UserRolePermissionRsp::getRoleId)
                .distinct()
                .collect(Collectors.toList());

        List<Long> permissionIds = userRolePermissionList
                .stream()
                .map(UserRolePermissionRsp::getPermissionId)
                .distinct()
                .collect(Collectors.toList());

        List<RoleRsp> roles = roleService.selectRoleListByIds(roleIds);

        List<PermissionRsp> permissions = permissionService.selectByPermissionListIds(permissionIds);

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (RoleRsp role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
        }

        for (PermissionRsp permission : permissions) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + permission.getPermissionCode()));
        }

        return new CustomUserDetails(user.getId(), username, user.getPassword(), authorities);
    }
}
