package com.yueking.core;

import com.yueking.core.dao.CategoryDao;
import com.yueking.core.dao.DepartmentDao;
import com.yueking.core.dao.SysDictDao;
import com.yueking.core.domain.Category;
import com.yueking.core.domain.Department;
import com.yueking.core.domain.DictPK;
import com.yueking.core.domain.SysDict;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CoreApplicationTests {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysDictDao dictDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    DepartmentDao departmentDao;

    @Test
    void contextLoads() {
    }

    @Test
    void addDict() {
        SysDict sex = new SysDict();
        sex.setName("性别");
        sex.setId(new DictPK("XBDM", "XBDM"));

        dictDao.save(sex);

        SysDict man = new SysDict();
        man.setName("男");
        man.setId(new DictPK("XBDM", "1"));
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

    @Test
    void addCategory() {
        Category category = new Category();
        category.setName("root");

        categoryDao.save(category);

        Category c1 = new Category();
        c1.setName("c1");

        c1.setParent(category);

        categoryDao.save(c1);

    }

    @Test
    void testAddDepartment() {
        Department root = new Department();
        root.setDepartmentName("性别");
        root.setId(0L);

        List<Department> list = new ArrayList<>();

        Department p1 = new Department();
        p1.setDepartmentName("男");
        p1.setParent(root);
        p1.setId(1L);

        Department p2 = new Department();
        p2.setDepartmentName("女");
        p2.setParent(root);
        p2.setId(2L);

        list.add(p1);
        list.add(p2);

        root.setChildren(list);

        departmentDao.save(root);
    }

    @Test
    void testLog() {
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...world");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }
}
