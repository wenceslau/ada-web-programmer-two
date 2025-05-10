package br.com.ada.springcontext.config;

import br.com.ada.springcontext.services.ExternalClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ExternalClass externalClass() {
        return new ExternalClass();
    }
}
