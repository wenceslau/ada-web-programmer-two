package br.com.ada.springsecurityjwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class AplicacaoController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String hello(){
        return "Hello World autenticado";
    }

    @GetMapping("/helloAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloAdmin(){
        return "Hello World Admin";
    }

    @GetMapping("/helloPublic")
    public String helloPublic(){
        return "Hello World Public";
    }

}
