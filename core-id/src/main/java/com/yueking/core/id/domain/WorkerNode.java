package com.yueking.core.id.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="worker_node")
public class WorkerNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hostName;
    private String port;
    private int type;
    private Date launchDate = new Date();

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date modified;
}