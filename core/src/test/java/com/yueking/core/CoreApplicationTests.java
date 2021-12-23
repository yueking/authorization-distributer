package com.yueking.core;

import com.yueking.core.dao.SysDictDao;
import com.yueking.core.domain.DictPK;
import com.yueking.core.domain.SysDict;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

    @Autowired
    private SysDictDao dictDao;

    @Test
    void contextLoads() {
    }

    @Test
    void addDict() {
        SysDict sex = new SysDict();
        sex.setName("性别");
        sex.setId(new DictPK("XBDM","XBDM"));

        dictDao.save(sex);

        SysDict man = new SysDict();
        man.setName("男");
        man.setId(new DictPK("XBDM","1"));
        man.setParent(sex);

        dictDao.save(man);

        SysDict woman = new SysDict();
        woman.setName("女");
        woman.setId(new DictPK("XBDM", "0"));
        woman.setParent(sex);

        dictDao.save(woman);
    }

    @Test
    void findDict() {
        SysDict dict = dictDao.findById(new DictPK("XBDM", "XBDM"));
        System.out.println(dict);
    }
}
