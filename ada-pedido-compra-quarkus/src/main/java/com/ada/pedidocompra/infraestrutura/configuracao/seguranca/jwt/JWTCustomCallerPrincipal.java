package com.ada.pedidocompra.infraestrutura.configuracao.seguranca.jwt;

import io.smallrye.jwt.auth.principal.*;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

@Priority(1)
@Alternative
@ApplicationScoped
public class JWTCustomCallerPrincipal extends JWTCallerPrincipalFactory {

    private static final Logger log = Logger.getLogger(JWTCustomCallerPrincipal.class.getName());


    @Override
    public JWTCallerPrincipal parse(String token, JWTAuthContextInfo authContextInfo) throws ParseException {
        System.out.println("JWTCustomCallerPrincipal.parse");

        JWTService.validateToken(token);

        try {
            String payload = new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]), StandardCharsets.UTF_8);

            var claims = JwtClaims.parse(payload);

            return new DefaultJWTCallerPrincipal(token, "JWT", claims);

        } catch (Exception e) {
            log.severe("Error extract JWT payload: " + e.getMessage());
            throw new ParseException("Error extract JWT payload", e);
        }
    }

}
