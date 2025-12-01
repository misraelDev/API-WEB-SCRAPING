package com.scraper.api_web_scraping.service;

import com.scraper.api_web_scraping.dto.ScrapeResponse;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class ScraperService {
    @PostConstruct
    public void init() {
        WebDriverManager.chromedriver().setup();
    }

    public ScrapeResponse scrapeUrl(String url) {
        ScrapeResponse response = new ScrapeResponse();
        response.setUrl(url);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver localDriver = null;
        try {
            localDriver = new ChromeDriver(options);
            localDriver.get(url);
            Thread.sleep(3000);
            String title = localDriver.getTitle();
            response.setTitle(title);
            String htmlContent = localDriver.getPageSource();
            response.setHtmlContent(htmlContent);
            String textContent = localDriver.findElement(
                org.openqa.selenium.By.tagName("body")
            ).getText();
            response.setTextContent(textContent);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError(e.getMessage());
            e.printStackTrace();
        } finally {
            if (localDriver != null) {
                localDriver.quit();
            }
        }
        return response;
    }

    @PreDestroy
    public void cleanup() {
        // Cleanup if needed
    }
}
