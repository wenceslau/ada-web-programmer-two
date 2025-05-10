package br.com.ada.jeejasrsrest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class ApplicationConfig extends Application {
    // Configuração base da API REST
    static {
        System.out.println("ApplicationConfig carregado com sucesso!");
    }
}

//mvn clean package wildfly:run
