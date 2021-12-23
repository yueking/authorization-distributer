package com.yueking.core.id.service;

public interface SerialNumberService {
    long getUid();
    String parseUid(long uid);
    String nextSerialNumber(String name, String unitCode)throws Exception;
}
