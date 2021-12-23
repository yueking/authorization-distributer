package com.yueking.core.id.dao.service;

public interface SerialNumberService {
    String nextSerialNumber(String name, String unitCode)throws Exception;
}
