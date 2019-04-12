package com.service;

import org.springframework.stereotype.Component;

/**
 * @author ZCZ
 * @create 2019-04-12-13:42
 */
@Component
public class ApiserviceError implements  Apiservice {
    @Override
    public String sss() {
        return "服务发生故障";
    }
}
