package com.yueking.core.dao;

import com.yueking.core.domain.DictPK;
import com.yueking.core.domain.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SysDictDao extends JpaRepository<SysDict,String> {
    SysDict findById(DictPK id);

    @Transactional
    void deleteById(DictPK id);

}
