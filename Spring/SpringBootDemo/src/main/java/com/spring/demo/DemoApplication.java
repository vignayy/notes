package com.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        System.out.println("Hello World");

        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        Alien obj = context.getBean(Alien.class);
        obj.code();
        System.out.println(obj.getAge());

    }

}
