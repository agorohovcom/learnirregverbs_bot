package com.agorohov.learnirregverbs_bot;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class IrregularverbsApplication {
    
    @PostConstruct
    void print(){
        log.info("The method with the @PostConstruct annotation worked");
    }

    public static void main(String[] args) {
        SpringApplication.run(IrregularverbsApplication.class, args);
    }

}
