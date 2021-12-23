package com.yueking.core.security.dao.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Entity
public class SysPermission implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "perm_id")
    private String id;
    private String permName;
    private String permTag;
    private String permDesc;

    @Override
    public String getAuthority() {
        return this.permTag;
    }
}
