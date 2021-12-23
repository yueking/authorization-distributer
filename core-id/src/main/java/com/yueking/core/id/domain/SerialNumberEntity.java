package com.yueking.core.id.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@Table(name="worker_node_sn")
public class SerialNumberEntity extends BaseEntity {
    //todo jap 1.注解的各种ID生成策略,2.自定义ID生成器

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "maxSeq")
    private String maxSEQ = null;

    @Column(name = "curDate")
    private String curDate = null;

    @Column(name = "code")
    private String code = null;

    @Column(name = "remark")
    private String remark = null;

    public SerialNumberEntity(String name) {
        this.name = name;
    }

    public SerialNumberEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}