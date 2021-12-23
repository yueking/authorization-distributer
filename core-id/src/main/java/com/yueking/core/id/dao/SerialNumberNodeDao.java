package com.yueking.core.id.dao;

import com.yueking.core.id.domain.SerialNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialNumberNodeDao extends JpaRepository<SerialNumberEntity,String> {
    SerialNumberEntity findByName(String name);
    @Modifying
    Long deleteByName(String name);

    @Modifying
    @Query(value = "delete from SerialNumberEntity s where s.name = :name")
    void deleteByNameSQL(String name);

    SerialNumberEntity findByNameAndCodeAndCurDate(String name, String code, String curDate);
}
