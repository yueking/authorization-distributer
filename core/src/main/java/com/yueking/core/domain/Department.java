package com.yueking.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

    public static final int STATUS_DISABLED = 0;

    public static final int STATUS_ENABLED = 1;

    @Column(nullable = false, columnDefinition="bigint COMMENT '唯一标识'")
    @Id
    private Long id;


    @Column(name = "name",nullable = false, columnDefinition = "varchar(255) COMMENT '部门名称'")
    private String departmentName;

//    @Column(nullable = false, columnDefinition = "varchar(1000) COMMENT '部门描述'")
//    private String departmentDesc;

//    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT 1 COMMENT '状态（1：可用，0：禁用）'")
//    private Integer status;


//    @Column(nullable = false, columnDefinition = "bigint(20) COMMENT '归属人ID'")
//    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID", columnDefinition = "bigint(20) COMMENT '父ID'")
    private Department parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Department> children = new ArrayList<>();

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(departmentName, that.departmentName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(departmentName)
                .toHashCode();
    }
}


