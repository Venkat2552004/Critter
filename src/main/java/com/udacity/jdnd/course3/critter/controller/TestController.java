package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.services.ConnectionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ConnectionTestService connectionTestService;

    @GetMapping("/test-pool")
    public String testConnectionPool() {
       
        connectionTestService.getConnectionFromPool();
        return "Request sent to the connection pool";
    }
}
