package com.ada.pedidocompra.quarkus.infraestrutura.configuracao.seguranca.jwt;

import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jose.crypto.MACVerifier;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import io.smallrye.jwt.auth.principal.ParseException;

import java.util.Date;
import java.util.Set;
import java.time.Instant;
import java.util.logging.Logger;


public class JWTService {

    private static final Logger log = Logger.getLogger(JWTService.class.getName());

    private static final String ISSUER = "https://issuer.org";
    private static final long DURATION = 1800; // 1/2 hour in seconds
    public static final String SECRET = "4453fd5e8408dc24655669d0a37ef72e";

    public static String createToken(String username, Set<String> roles) {
        JwtClaimsBuilder claimsBuilder = Jwt.claims()
                .issuer(ISSUER)
                .subject(username)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(DURATION))
                .groups(roles);


        return claimsBuilder.jws().signWithSecret(SECRET);
    }

    public static void validateToken(String token) throws ParseException {
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);

            if (!signedJWT.verify(new MACVerifier(SECRET))) {
                throw new RuntimeException("Invalid JWT signature");
            }

            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            if (expirationTime != null && expirationTime.before(new Date())) {
                throw new RuntimeException("Token expired");
            }

        } catch (Exception e) {
            log.warning("Error validate token: " + e.getMessage());
            throw new ParseException("Error validate token", e);
        }
    }

}
