package com.Item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.stereotype.Controller;




@Controller
@SpringBootApplication
@EnableAutoConfiguration
public class CrudItemsApplication extends SpringBootServletInitializer  {
	
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(CrudItemsApplication.class);
	    }
	

	public static void main(String[] args) throws Exception{
		SpringApplication.run(CrudItemsApplication.class, args);
	}

}
