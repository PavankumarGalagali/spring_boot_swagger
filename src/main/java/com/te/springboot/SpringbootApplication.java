package com.te.springboot;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public Docket getDocket() {
	  return  new Docket(DocumentationType.SWAGGER_2)
			  .select()
			  .paths(PathSelectors.ant("/**"))
			  .apis(RequestHandlerSelectors.basePackage("com.te.springboot"))
			  .build()
			  .apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("EMS Documentation", "API DOC", "1.0", "From TechoElevat",
				new springfox.documentation.service.Contact("Pavan", "TechnoElevate", "pavan@gmail.com"), "API License",
				"technoElevate.com", Collections.emptyList());
	}
}
