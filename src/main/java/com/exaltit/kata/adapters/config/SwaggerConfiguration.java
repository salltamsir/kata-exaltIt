package com.exaltit.kata.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    public Docket serviceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.exaltit.kata"))
                .paths(regex("/*.*"))
                .build()
                .apiInfo(appMetaData());
    }

    private ApiInfo appMetaData() {
        return new ApiInfoBuilder()
                .title("BANK ACCOUNT REST API")
                .description("Account")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact("Tamsir SALL",
                        "https://www.linkedin.com/in/tamsir-ousmane-sall-929051157",
                        "salltamsirousmane@gmail.com"))
                .build();
    }
}
