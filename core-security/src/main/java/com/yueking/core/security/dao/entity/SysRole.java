package com.yueking.core.security.dao.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Data
@Entity
public class SysRole implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "role_id")
    private Long id;
    private String roleName;
    private String roleTag;
    private String roleDesc;

    public void addPermission(SysPermission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(SysPermission permission) {
        this.permissions.remove(permission);
    }

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    public List<SysPermission> permissions = new LinkedList<>();

    @Override
    public String getAuthority() {
        return this.roleTag;
    }
}
