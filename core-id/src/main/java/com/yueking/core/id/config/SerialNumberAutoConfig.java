package com.yueking.core.id.config;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.yueking.core.id.assigner.MyDisposableWorkerIdAssigner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SerialNumberAutoConfig {
    @Bean
    @ConfigurationProperties("serial.number")
    public SerialNumberProperties getSerialNumberConfig() {
        return new SerialNumberProperties();
    }

    @Bean
    public MyDisposableWorkerIdAssigner disposableWorkerIdAssigner(){
        return new MyDisposableWorkerIdAssigner();
    }

    @Bean
    public CachedUidGenerator cachedUidGenerator(MyDisposableWorkerIdAssigner disposableWorkerIdAssigner){
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);

        cachedUidGenerator.setTimeBits(29);
        cachedUidGenerator.setWorkerBits(21);
        cachedUidGenerator.setSeqBits(13);
        cachedUidGenerator.setEpochStr("2021-02-20");
        cachedUidGenerator.setBoostPower(3);
        cachedUidGenerator.setScheduleInterval(60L);
        return cachedUidGenerator;
    }
}
