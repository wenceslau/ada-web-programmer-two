package com.ada.estacionamento.infraestrutura.config;

import com.ada.estacionamento.infraestrutura.repositorios.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceSecurity implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserServiceSecurity(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UserSecurity(usuario.getUsername(), usuario.getPassword());
    }

}
