package com.ada.estacionamento.infraestrutura.controladores;

import com.ada.estacionamento.infraestrutura.controladores.records.UsuarioRequest;
import com.ada.estacionamento.infraestrutura.repositorios.UsuarioRepository;
import com.ada.estacionamento.infraestrutura.repositorios.entidades.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        try {

            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setUsername(usuarioRequest.username());
            usuarioEntity.setPassword(passwordEncoder.encode(usuarioRequest.senha()));
            usuarioRepository.save(usuarioEntity);

            return ResponseEntity
                    .status(201)
                    .body("Usuário criado com sucesso");

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

}
