package com.huanglei.springbootdemo1;

import com.huanglei.springbootdemo1.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Springbootdemo1Application {

    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/")
    String index(){
//        return "Hello Spring Boot";
        return "author name is "+authorSettings.getName()+" and author age is "+authorSettings.getAge();
    }
    public static void main(String[] args) {
        SpringApplication.run(Springbootdemo1Application.class, args);
    }

}
