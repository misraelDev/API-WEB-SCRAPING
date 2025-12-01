package com.scraper.api_web_scraping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapeResponse {
    private String url;
    private String title;
    private String htmlContent;
    private String textContent;
    private boolean success;
    private String error;
}
