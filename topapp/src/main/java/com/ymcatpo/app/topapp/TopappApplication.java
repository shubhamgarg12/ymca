package com.ymcatpo.app.topapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(exclude = JmsAutoConfiguration.class)
@EnableAsync
public class TopappApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TopappApplication.class, args);
	}
	
	
}
