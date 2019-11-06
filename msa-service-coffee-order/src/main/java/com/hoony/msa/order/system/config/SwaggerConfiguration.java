package com.hoony.msa.order.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hoony.msa.order.springboot") )
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getArrayList());
    }

    private ArrayList<ResponseMessage> getArrayList(){
        ArrayList<ResponseMessage> lists = new ArrayList<ResponseMessage>();

//        lists.add(new ResponseMessageBuilder().code(500).message("Server Error")
//                .responseModel(new ModelRef("Error")).build());
//        lists.add(new ResponseMessageBuilder().code(403).message("No Authentication")
//                .responseModel(new ModelRef("Error")).build());
//        lists.add(new ResponseMessageBuilder().code(400).message("Some error message")
//                .responseModel(new ModelRef("Error")).build());
//        lists.add(new ResponseMessageBuilder().code(401).message("Some error message")
//                .responseModel(new ModelRef("Error")).build());
//        lists.add(new ResponseMessageBuilder().code(404).message("Some error message")
//                .responseModel(new ModelRef("Error")).build());
        return lists;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Server API",
                "Server of API.",
                "1.0.0",
                "",
                new Contact("hoony", "http://hoony.com", ""),
                "Apache2.0", "API license URL", Collections.emptyList());
    }
}
