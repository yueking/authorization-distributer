package com.yueking.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "sys_dict")
public class SysDict implements Serializable {
    @Id
    private DictPK id;

    @Column(name = "name", length = 30)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "parent_Key"),
            @JoinColumn(name = "parent_type")
    })
    private SysDict parent;

    @OneToMany(targetEntity = SysDict.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "parent")
    private Set<SysDict> children = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysDict)) return false;
        SysDict sysDict = (SysDict) o;
        return new EqualsBuilder()
                .append(id, sysDict.id)
                .append(name, sysDict.name)
                .append(children, sysDict.children)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(children)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SysDict{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
