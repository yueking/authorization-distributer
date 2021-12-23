package com.yueking.core.id.config;

import com.yueking.core.id.domain.SerialNumberEntity;
import com.yueking.core.id.utils.DateUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;

@Data
public class SerialNumberProperties implements Serializable {
    private String name = null;
    private String prefix = null;
    private String datePattern = "yyyyMM";
    private int numberLength = 6;

    private Map<String, SerialNumberProperties> kinds;

    public static SerialNumberEntity createSerialNumberEntityByConfig(SerialNumberProperties config, String unitCode) throws Exception {
        SerialNumberEntity model = new SerialNumberEntity();
        if (config == null || StringUtils.isEmpty(config.getName()) || StringUtils.isEmpty(config.getDatePattern())) {
            throw new Exception("序列号配置器错误!");
        }
        if (StringUtils.isNotEmpty(unitCode)) {
            model.setCode(unitCode);
        }
        model.setName(config.getName().trim());
        model.setCurDate(DateUtil.getDataStringFormPattern(config.getDatePattern()));
        return model;
    }
}