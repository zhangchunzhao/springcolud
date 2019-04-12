package com.handler;

import com.service.Apiservice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZCZ
 * @create 2019-04-12-14:26
 */
@RestController
public class ApiHandler {
    @Resource
    private Apiservice apiService;

    @RequestMapping("index")
    public String index() {
        return apiService.sss();
    }

}
