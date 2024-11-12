package com.project.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//import com.project.demo.config.JwtFilter;
import com.project.demo.models.Admin;
import com.project.demo.service.AdminService;

@SpringBootApplication
public class FoodorderingsystemprojectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(FoodorderingsystemprojectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FoodorderingsystemprojectApplication.class, args);
		System.out.println("Online Food Ordering System Started!");
	}
	
	@Bean
	public CommandLineRunner demo(AdminService srv) {
	    return (args) -> {
	    	if(srv.count()==0) {
	    		srv.updateAdmin(new Admin("admin", "admin", "Administrator"));
	    		log.info("Admin user created successfully");
	    		
	    	}
	    };
	}

}


	
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.addUrlPatterns("/api/*");
//        return registrationBean;
//    }

	
	



