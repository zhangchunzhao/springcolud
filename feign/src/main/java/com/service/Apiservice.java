package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ZCZ
 * @create 2019-04-11-16:20
 */
@FeignClient(value = "eurekaclient",fallback = ApiserviceError.class)
public interface Apiservice {
     @RequestMapping(value = "/sss",method = RequestMethod.GET)
     String sss();
}
