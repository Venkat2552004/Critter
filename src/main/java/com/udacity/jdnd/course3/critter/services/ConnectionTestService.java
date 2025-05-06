package com.udacity.jdnd.course3.critter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class ConnectionTestService {

    @Autowired
    private DataSource dataSource;

    @Async
    public void getConnectionFromPool() {
        try (Connection connection = dataSource.getConnection()) {
            // Simulate a long database query
            Thread.sleep(5000);  // Simulate a 5-second query
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
