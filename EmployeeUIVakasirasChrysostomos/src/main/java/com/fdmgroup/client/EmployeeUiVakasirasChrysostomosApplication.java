package com.fdmgroup.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.client.exception.CustomErrorDecoder;

@SpringBootApplication
@EnableFeignClients
public class EmployeeUiVakasirasChrysostomosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeUiVakasirasChrysostomosApplication.class, args);
	}

	@Bean
	WebClient.Builder webClientBuilder(){
		return WebClient.builder().baseUrl("http://localhost:8088");
	}

    @Bean
    CustomErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}
