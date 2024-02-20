package com.Icekiwi.Freeread.packageconfig;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

//@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Gestion stock pharmacies API documentation")
                                .title("Gestion stock pharmacies REST API")
                                .build()
                )
                .groupName("REST API V1.0")
//                .securityContexts(Collections.singletonList(securityContext()))
//                .securitySchemes(Collections.singletonList(apiKey()))
//                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Icekiwi.Freeread.dao.entities"))
                .paths(PathSelectors.any())
                .build();
    }}
