package com.yueking.core.id.dao.service.impl;

import com.yueking.core.id.config.SerialNumberProperties;
import com.yueking.core.id.dao.entity.SerialNumberEntity;
import com.yueking.core.id.dao.repository.SerialNumberNodeDao;
import com.yueking.core.id.dao.service.SerialNumberService;
import com.yueking.core.id.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;

@Service
public class SerialNumberServiceImpl implements SerialNumberService {
    @Resource
    private SerialNumberProperties serialNumberConfig;

    @Resource
    private SerialNumberNodeDao serialNumberDao;

    @Override
    public String nextSerialNumber(String name, String unitCode) throws Exception {
        //1.读取配置文件
        SerialNumberProperties config = serialNumberConfig.getKinds().get(name);
        if (config == null) {
            throw new Exception("找不到"+name+"serialNumber配置");
        }
        //2.根据配置文件 配置 modelForQuery 用于查找model 如存在 拿来用,如不在 创建一个 插入并使用
        SerialNumberEntity modelForQuery = SerialNumberProperties.createSerialNumberEntityByConfig(config, unitCode);
        DecimalFormat format = new DecimalFormat(StringUtils.repeat("0", config.getNumberLength()));
        String maxNo = null;
        StringBuilder sn = new StringBuilder();

        //3.很据sequenceMode 查找数据库中有没有保存该信息,
        SerialNumberEntity modelEntry = serialNumberDao.findByNameAndCodeAndCurDate(name,unitCode,DateUtil.getDataStringFormPattern(config.getDatePattern()));
        if (modelEntry == null) {
            //4.如果数据库中不存在 将 生成一个新的数据 插入到数据库中
            maxNo = format.format(1);
            System.out.println(maxNo);
            modelEntry = modelForQuery;
            modelEntry.setMaxSEQ(maxNo);
            serialNumberDao.save(modelForQuery);
        } else {
            //4.如果数据库中存在 拿来使用 并且更新 maxSEQ + 1
            maxNo = format.format(Long.parseLong(modelEntry.getMaxSEQ()) + 1);
            modelEntry.setMaxSEQ(maxNo);
            serialNumberDao.saveAndFlush(modelEntry);
        }
        if (config.getPrefix() != null && !config.getPrefix().trim().equals("")) {
            sn.append(config.getPrefix());
        }
        if (modelEntry.getCode() != null && !modelEntry.getCode().trim().equals("")) {
            sn.append(modelEntry.getCode());
        }
        sn.append(modelEntry.getCurDate());
        sn.append(maxNo);
        return sn.toString();
    }
}
