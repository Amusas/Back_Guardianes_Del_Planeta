package com.guardianesdelplaneta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuardianesPlanetaApplication {

    private static final Logger log = LoggerFactory.getLogger(GuardianesPlanetaApplication.class);

    public static void main(String[] args) {
        log.info("Iniciando aplicación Guardianes del Planeta...");
        SpringApplication.run(GuardianesPlanetaApplication.class, args);
        log.info("Aplicación Guardianes del Planeta iniciada correctamente.");
    }
}
