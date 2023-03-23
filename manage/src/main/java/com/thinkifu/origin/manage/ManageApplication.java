package com.thinkifu.origin.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.thinkifu.origin.*.*"})
@EnableTransactionManagement
public class ManageApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ManageApplication.class);
    }
}
