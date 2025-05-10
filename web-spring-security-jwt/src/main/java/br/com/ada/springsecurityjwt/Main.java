package br.com.ada.springsecurityjwt;

import br.com.ada.springsecurityjwt.repositories.Usuario;
import br.com.ada.springsecurityjwt.repositories.UsuarioRepository;
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
    CommandLineRunner initData(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            var user = new Usuario();
            user.setUsuario("user");
            user.setSenha(passwordEncoder.encode("1234"));
            user.setPerfil("USER");
            userRepository.save(user);

            var admin = new Usuario();
            admin.setUsuario("admin");
            admin.setSenha(passwordEncoder.encode("1234"));
            admin.setPerfil("ADMIN");
            userRepository.save(admin);

            var operator = new Usuario();
            operator.setUsuario("operador");
            operator.setSenha(passwordEncoder.encode("1234"));
            operator.setPerfil("OPERATOR");
            userRepository.save(operator);
        };
    }

}
