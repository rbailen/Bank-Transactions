package com.codechallenge.banktransactions.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /** The Constant DEFAULT_CONTACT. */
    public static final Contact DEFAULT_CONTACT = new Contact("rbailen", "https://es.linkedin.com/in/ramonbailensanchez", "ramonbailen10@gmail.com");

    /** The Constant DEFAULT_API_INFO. */
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Bank Transactions API", "API that will handle bank transactions", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
                .apis(RequestHandlerSelectors.basePackage("com.codechallenge.banktransactions")).paths(PathSelectors.any()).build();
    }
}