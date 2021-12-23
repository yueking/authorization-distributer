package com.yueking.core.id.service;

public interface SerialNumberService {
    String nextSerialNumber(String name, String unitCode)throws Exception;
}
