package com.OneClick.hackathon.config;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Contact seatSearchContact = new Contact("OCP Team", null, "ocp-team@ticketmaster.com");

        ApiInfo apiInfo = new ApiInfoBuilder()
            .contact(seatSearchContact)
            .title("OCP API")
            .description("OCP REST API.")
            .build();

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("ocp")
            .apiInfo(apiInfo)
            .select()
            .paths(PathSelectors.regex("/api/.*"))
            .build();
    }


    /**
     * Many people here expect docs at this location.
     */
    @RequestMapping(path={"/api-docs", "/api-docs.html"})
    public void redirectToApiDocs(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
