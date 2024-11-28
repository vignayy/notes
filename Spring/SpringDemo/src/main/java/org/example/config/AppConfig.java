package org.example.config;

import org.example.Alien;
import org.example.Computer;
import org.example.Desktop;
import org.example.Laptop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
//@ComponentScan("org.example")    --> Component Stereotype Annotation
public class AppConfig {


    @Bean
    public Alien alien(Computer com) //@Qualifier("desktop")
    {
        Alien obj = new Alien();
        obj.setAge(25);
        obj.setCom(com);
        return obj;

    }


    @Bean
    @Primary
    public Laptop laptop() {
        return new Laptop();
    }


//	@Bean(name = {"Beast","desktop","com1"})

    @Bean
    //@Scope("prototype")
    public Desktop desktop() {
        return new Desktop();
    }
}