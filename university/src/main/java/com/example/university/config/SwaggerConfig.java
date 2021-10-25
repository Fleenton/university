package com.example.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;

@Configuration
public class SwaggerConfig {

    private final Docket docket;

    @Autowired
    public SwaggerConfig(Docket docket) {
        this.docket = docket;
    }

    @PostConstruct
    public void adjustDocketConfiguration() {
        docket.apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("University REST API").description("<PUT_DESCRIPTION_HERE>").build();
    }
}