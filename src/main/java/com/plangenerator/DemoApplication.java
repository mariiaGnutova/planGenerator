package com.plangenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication

public class DemoApplication implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
				.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}


	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());
	}


	private ApiInfo generateApiInfo()
	{
		return new ApiInfo("Plan Generator Server Applicant Test Service", "This service is to inform borrowers about the final repayment schedule" +
				"throughout the lifetime of a loan.", "Version 1.0 ",
				"urn:tos", "gnutovamaria85@gmail.com", "", "");
	}
}
