package com.udacity.jdnd.course3.critter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:datasource.properties")
@PropertySource("classpath:server.properties")
@PropertySource("classpath:jpa.properties")
public class AppConfig {
    
}
