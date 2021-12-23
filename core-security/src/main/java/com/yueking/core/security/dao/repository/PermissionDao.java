package com.yueking.core.security.dao.repository;

import com.yueking.core.security.dao.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<SysPermission, String> {
    SysPermission getSysPermissionByPermName(String permissionName);
}
