package com.yueking.core.security.dao.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class SysUser implements UserDetails, Serializable {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "user_id")
    // private Long id;
    private String username;
    private String password;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<SysRole> roles = new LinkedList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List authorities = new LinkedList();
        for (SysRole role : roles) {
            authorities.add(role);
            List<SysPermission> permissions = role.getPermissions();
            for (SysPermission permission : permissions) {
                authorities.add(permission);
            }
        }
        return authorities;
    }

    public void addRole(SysRole role) {
        this.roles.add(role);
    }

    public void removeRole(SysRole role) {
        this.roles.remove(role);
    }
}