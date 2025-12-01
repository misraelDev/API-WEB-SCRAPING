# API Web Scraping


![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)
![License](https://img.shields.io/badge/license-MIT-green)


## Descripción

> [!IMPORTANT]
> Este proyecto es una API REST desarrollada en Java con Spring Boot para realizar web scraping de manera eficiente y escalable. Permite extraer información de páginas web mediante solicitudes HTTP y devolver los datos estructurados en formato JSON.


## Características
- Arquitectura basada en Spring Boot
- Endpoints REST para iniciar scraping
- Manejo de solicitudes y respuestas con DTOs
- Servicio centralizado para lógica de scraping
- Pruebas unitarias y de integración
- Configuración flexible mediante `application.properties`

> [!TIP]
> Puedes adaptar los selectores y la lógica de scraping fácilmente en el servicio centralizado.

## Estructura del Proyecto
```
api-web-scraping/
├── src/
│   ├── main/
│   │   ├── java/com/scraper/api_web_scraping/
│   │   │   ├── ApiWebScrapingApplication.java
│   │   │   ├── controller/
│   │   │   │   └── ScraperController.java
│   │   │   ├── dto/
│   │   │   │   ├── ScrapeRequest.java
│   │   │   │   └── ScrapeResponse.java
│   │   │   └── service/
│   │   │       └── ScraperService.java
│   │   └── resources/application.properties
│   └── test/java/com/scraper/api_web_scraping/
│       ├── ApiWebScrapingApplicationTests.java
│       ├── TestApiWebScrapingApplication.java
│       └── TestcontainersConfiguration.java
├── pom.xml
└── README.md
```


## Instalación y Ejecución

> [!NOTE]
> Antes de comenzar, asegúrate de tener instalado Java 17+ y Maven 3.8+.

### Prerrequisitos
- Java 17 o superior
- Maven 3.8+

### Clonar el repositorio
```powershell
git clone https://github.com/misraelDev/API-WEB-SCRAPING.git
cd API-WEB-SCRAPING
```


### Construir el proyecto
```powershell
mvn clean install
```

### Ejecutar la API
```powershell
mvn spring-boot:run
```

> [!WARNING]
> La API estará disponible en `http://localhost:8080`. Cambia el puerto en `application.properties` si es necesario.


## Uso de la API

> [!CAUTION]
> No envíes demasiadas solicitudes en poco tiempo para evitar bloqueos por parte de los sitios web objetivo.

### Endpoint principal
`POST /api/scrape`

#### Ejemplo de solicitud
```json
{
  "url": "https://ejemplo.com",
  "selectors": [".titulo", ".precio"]
}
```

#### Ejemplo de respuesta
```json
{
  "data": {
    "titulo": "Producto Ejemplo",
    "precio": "$100"
  },
  "status": "success"
}
```


## Configuración

> [!NOTE]
> Edita el archivo `src/main/resources/application.properties` para personalizar la configuración de la API (puerto, logs, etc).


## Pruebas

> [!TIP]
> Ejecuta las pruebas con:
```powershell
mvn test
```


## Contribuir

> [!IMPORTANT]
> Por favor, sigue el flujo estándar de GitHub para contribuir:
> 1. Haz un fork del repositorio
> 2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`)
> 3. Realiza tus cambios y haz commit
> 4. Haz push a tu rama y crea un Pull Request


## Licencia
Este proyecto está bajo la licencia MIT.

---

> [!NOTE]
> ¡Gracias por contribuir y usar API Web Scraping! Si tienes dudas o sugerencias, abre un issue en GitHub.
