package br.com.ada.springsecuritybasic;

import br.com.ada.springsecuritybasic.repositories.UserRepository;
import br.com.ada.springsecuritybasic.repositories.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableMethodSecurity
@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            var user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole("USER");
            userRepository.save(user);

            var admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole("ADMIN");
            userRepository.save(admin);

            var operator = new Usuario();
            operator.setUsername("operador");
            operator.setPassword(passwordEncoder.encode("1234"));
            operator.setRole("OPERATOR");
            userRepository.save(operator);
        };
    }


}
