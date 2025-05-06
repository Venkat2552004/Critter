package com.udacity.jdnd.course3.critter.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix = "author")
public class AuthorController {
    String name;
    String eid;

    @GetMapping("/author")
    public String getAuthorDetails(){
        return "Author name : " + name + " and the enterprise id is : " + eid;
    }
}
