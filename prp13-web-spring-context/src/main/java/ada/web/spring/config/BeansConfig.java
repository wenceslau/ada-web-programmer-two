package ada.web.spring.config;

import ada.web.spring.services.ExternalClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ExternalClass externalClass() {
        return new ExternalClass();
    }
}
