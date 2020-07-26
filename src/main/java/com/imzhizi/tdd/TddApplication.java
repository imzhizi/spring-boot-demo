package com.imzhizi.tdd;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * created by zhizi
 * on 5/31/20 09:33
 */
@Log4j2
@SpringBootApplication
public class TddApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}