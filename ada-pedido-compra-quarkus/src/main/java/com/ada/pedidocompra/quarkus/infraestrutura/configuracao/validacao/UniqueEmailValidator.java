package com.ada.pedidocompra.quarkus.infraestrutura.configuracao.validacao;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UsuarioRepository repository;

    public UniqueEmailValidator(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null)
            return true;
        return repository.findByEmail(email).isEmpty();
    }

}
