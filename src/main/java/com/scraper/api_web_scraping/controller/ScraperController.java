package com.scraper.api_web_scraping.controller;

import com.scraper.api_web_scraping.dto.ScrapeRequest;
import com.scraper.api_web_scraping.dto.ScrapeResponse;
import com.scraper.api_web_scraping.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para realizar web scraping de páginas web
 * Expone endpoints para extraer contenido completo de URLs proporcionadas
 * 
 * @author Tu nombre
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/v1/scraper")
public class ScraperController {
    
    @Autowired
    private ScraperService scraperService;

    /**
     * Endpoint POST para realizar web scraping de una URL
     * 
     * Características:
     * - Recibe la URL en el cuerpo de la petición como JSON
     * - Más seguro y apropiado para URLs largas o con caracteres especiales
     * - Ideal para integraciones entre sistemas y aplicaciones frontend
     * - Permite extensibilidad futura (agregar más parámetros en el body)
     * 
     * Ejemplo de uso con cURL:
     * <pre>
     * curl -X POST http://localhost:8080/api/v1/scraper/scrape \
     *   -H "Content-Type: application/json" \
     *   -d '{"url": "https://www.wikipedia.org"}'
     * </pre>
     * 
     * Ejemplo de uso con Postman:
     * - Método: POST
     * - URL: http://localhost:8080/api/v1/scraper/scrape
     * - Headers: Content-Type: application/json
     * - Body (raw JSON): {"url": "https://www.wikipedia.org"}
     * 
     * @param request objeto ScrapeRequest que contiene la URL a scrapear
     * @return ResponseEntity con ScrapeResponse conteniendo:
     *         - 200 OK: scraping exitoso con el contenido de la página
     *         - 400 BAD REQUEST: si la URL está vacía o es nula
     *         - 500 INTERNAL SERVER ERROR: si ocurre un error durante el scraping
     */
    @PostMapping("/scrape")
    public ResponseEntity<ScrapeResponse> scrapePage(@RequestBody ScrapeRequest request) {
        // Validación de entrada: verificar que la URL no sea nula o vacía
        if (request.getUrl() == null || request.getUrl().isEmpty()) {
            ScrapeResponse errorResponse = new ScrapeResponse();
            errorResponse.setSuccess(false);
            errorResponse.setError("URL is required");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        // Delegar la lógica de scraping al servicio
        ScrapeResponse response = scraperService.scrapeUrl(request.getUrl());
        
        // Retornar respuesta apropiada según el resultado
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Endpoint GET para realizar web scraping de una URL
     * 
     * Características:
     * - Recibe la URL como parámetro de consulta (query parameter)
     * - Más simple y directo para pruebas rápidas
     * - Ideal para usar directamente desde el navegador
     * - Limitado en URLs muy largas o con caracteres especiales
     * 
     * Ventajas:
     * - Facilita pruebas rápidas sin necesidad de herramientas adicionales
     * - Compatible con enlaces directos (se puede compartir la URL completa)
     * - Útil para webhooks o integraciones simples
     * 
     * Ejemplo de uso directo en navegador:
     * http://localhost:8080/api/v1/scraper/scrape?url=https://www.wikipedia.org
     * 
     * Ejemplo de uso con cURL:
     * <pre>
     * curl "http://localhost:8080/api/v1/scraper/scrape?url=https://www.wikipedia.org"
     * </pre>
     * 
     * Nota: Para URLs con caracteres especiales, asegúrate de codificarlas correctamente (URL encoding)
     * 
     * @param url la URL de la página web a scrapear (query parameter)
     * @return ResponseEntity con ScrapeResponse conteniendo:
     *         - 200 OK: scraping exitoso con el contenido de la página
     *         - 500 INTERNAL SERVER ERROR: si ocurre un error durante el scraping
     */
    @GetMapping("/scrape")
    public ResponseEntity<ScrapeResponse> scrapePageGet(@RequestParam String url) {
        // Delegar la lógica de scraping al servicio
        ScrapeResponse response = scraperService.scrapeUrl(url);
        
        // Retornar respuesta apropiada según el resultado
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).body(response);
        }
    }
}