package com.yueking.core.id;


import com.yueking.core.id.config.SerialNumberProperties;
import com.yueking.core.id.service.SerialNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CoreIdApplicationTests {

    @Resource
    private SerialNumberProperties serialNumberProperties;

    @Value("${serial.number.kinds.ht.name}")
    private String password;

    @Autowired
    private SerialNumberService serialNumberService;

    @Test
    void contextLoads() {
        System.out.println("password:"+password);

        SerialNumberProperties name1 = serialNumberProperties.getKinds().get("name1");
        SerialNumberProperties name2 = serialNumberProperties.getKinds().get("name2");

        System.out.println(name1);
        System.out.println(name2);
    }

    @Test
    void testId() throws Exception {
        String name1 = serialNumberService.nextSerialNumber("ht", "41");
        System.out.println(name1);
    }



}
