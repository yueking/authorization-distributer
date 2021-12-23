package com.yueking.core.id.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Version
    private Long version;

    @XmlTransient
    private boolean del;

    @Column(name = "createDate")
    @CreatedDate
    @XmlTransient
    private Date createDate;

    @Column(name = "lastModifiedDate")
    @LastModifiedDate
    @XmlTransient
    private Date lastModifiedDate;
}
