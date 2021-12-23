package com.yueking.core.id.assigner;

import com.baidu.fsg.uid.utils.DockerUtils;
import com.baidu.fsg.uid.utils.NetUtils;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerNodeType;

import com.yueking.core.id.dao.WorkerNodeDao;
import com.yueking.core.id.domain.WorkerNode;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Date;

public class MyDisposableWorkerIdAssigner implements WorkerIdAssigner {
    @Autowired
    private WorkerNodeDao workerNodeDao;

    @Override
    @Transactional
    public long assignWorkerId() {
        WorkerNode workerNode = buildWorkerNode();
        workerNodeDao.addWorkerNode(workerNode);
        return workerNode.getId();
    }
    private WorkerNode buildWorkerNode(){
        WorkerNode workerNodeEntity = new WorkerNode();
        if (DockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkerNodeType.CONTAINER.value());
            workerNodeEntity.setHostName(DockerUtils.getDockerHost());
            workerNodeEntity.setPort(DockerUtils.getDockerPort());
        } else {
            workerNodeEntity.setType(WorkerNodeType.ACTUAL.value());
            workerNodeEntity.setHostName(NetUtils.getLocalAddress());
            workerNodeEntity.setPort(System.currentTimeMillis()+"-"+ RandomUtils.nextInt(100000));
        }
        workerNodeEntity.setCreated(new Date());
        workerNodeEntity.setModified(new Date());
        workerNodeEntity.setLaunchDate(new Date());
        return workerNodeEntity;
    }
}
