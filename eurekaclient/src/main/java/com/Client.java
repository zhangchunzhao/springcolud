package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZCZ
 * @create 2019-04-11-15:38
 */
@RestController
public class Client {
    @Value("${server.port}")
    private int port;

    @RequestMapping("sss")
    public String ss(){
        return  "sdasdadas "+port;
    }
}
