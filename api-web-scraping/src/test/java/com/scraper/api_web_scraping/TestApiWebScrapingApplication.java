package com.scraper.api_web_scraping;

import org.springframework.boot.SpringApplication;

public class TestApiWebScrapingApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiWebScrapingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
