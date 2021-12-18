package com.yueking.core.security;


import com.yueking.core.security.dao.entity.SysPermission;
import com.yueking.core.security.dao.entity.SysRole;
import com.yueking.core.security.dao.entity.SysUser;
import com.yueking.core.security.dao.repository.PermissionDao;
import com.yueking.core.security.dao.repository.RoleDao;
import com.yueking.core.security.dao.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class SpringBootJpaTester {
    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void addPerm(){
        SysPermission p1 = new SysPermission();
        p1.setPermName("addPerm");
        p1.setPermTag("addMember");
        p1.setPermDesc("addPermDesc");
        permissionDao.saveAndFlush(p1);

        SysPermission p2 = new SysPermission();
        p2.setPermName("delPerm");
        p2.setPermTag("delMember");
        p2.setPermDesc("delPermDesc");
        permissionDao.saveAndFlush(p2);

        SysPermission p3 = new SysPermission();
        p3.setPermName("updatePerm");
        p3.setPermTag("updateMember");
        p3.setPermDesc("updatePermDesc");
        permissionDao.saveAndFlush(p3);

        SysPermission p4 = new SysPermission();
        p4.setPermName("showPerm");
        p4.setPermTag("showMember");
        p4.setPermDesc("showPermDesc");
        permissionDao.saveAndFlush(p4);
    }

    @Test
    void addRole(){
        SysRole adminRole = new SysRole();
        adminRole.setRoleName("adminRole");
        adminRole.setRoleTag("adminRoleTag");
        adminRole.setRoleDesc("管理员角色");

        roleDao.saveAndFlush(adminRole);

        SysRole userRole = new SysRole();
        userRole.setRoleName("userRole");
        userRole.setRoleTag("userRoleTag");
        userRole.setRoleDesc("员工角色");

        roleDao.saveAndFlush(userRole);
    }

    @Test
    void modifyRole(){
        SysRole role_admin = roleDao.findById(1l).get();
        SysRole role_user = roleDao.findById(2l).get();

        List<SysPermission> permissionList = permissionDao.findAll();
        role_admin.setPermissions(permissionList);

        role_user.addPermission(permissionDao.findById(4l).get());

        roleDao.saveAndFlush(role_admin);
        roleDao.saveAndFlush(role_user);


    }


    @Test
    void addAdminUser() {
        SysUser admin = new SysUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));

        SysUser user = new SysUser();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));

        SysRole adminRole = roleDao.findById(1l).get();
        SysRole userRole = roleDao.findById(2l).get();

        admin.addRole(adminRole);
        user.addRole(userRole);

        userDao.saveAndFlush(admin);
        userDao.saveAndFlush(user);
    }

    @Test
    void showUser(){
        SysUser sysUser = userDao.findById("admin").get();
        Collection<? extends GrantedAuthority> authorities = sysUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
        }
    }
}
