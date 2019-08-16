package com.newrelic.apmplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class ApmPlatformApplication extends  SpringBootServletInitializer {

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApmPlatformApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(ApmPlatformApplication.class, args);
	}

}
