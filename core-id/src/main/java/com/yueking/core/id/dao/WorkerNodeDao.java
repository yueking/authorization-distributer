package com.yueking.core.id.dao;


import com.yueking.core.id.domain.WorkerNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface WorkerNodeDao extends Serializable, JpaRepository<WorkerNode,Long> {
    @Query(value = "select * from worker_node where HOST_NAME=:host and port=:port",nativeQuery = true)
    WorkerNode getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);
    @Query(value = "insert into worker_node(ID, HOST_NAME, PORT, TYPE, LAUNCH_DATE, MODIFIED, CREATED)values(:#{#workerNodeEntity.id},:#{#workerNodeEntity.hostName},:#{#workerNodeEntity.port},:#{#workerNodeEntity.type},:#{#workerNodeEntity.launchDate},:#{#workerNodeEntity.modified},:#{#workerNodeEntity.created})",nativeQuery = true)
    @Modifying
    @Transactional
    void addWorkerNode(@Param("workerNodeEntity") WorkerNode workerNodeEntity);

}
