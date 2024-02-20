package com.Icekiwi.Freeread;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableScheduling
//@EntityScan(basePackages = {"com.Icekiwi.Freeread"})
//@EnableJpaRepositories(basePackages = {"com.Icekiwi.Freeread"})
public class FreereadApplication {


	public static void main(String[] args) {
		SpringApplication.run(FreereadApplication.class, args);
	}

}
