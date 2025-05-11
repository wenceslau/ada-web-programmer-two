package com.ada.estacionamento.infraestrutura.config;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.logging.Logger;

public class PasswordUtils {

    private static final Logger log = Logger.getLogger(PasswordUtils.class.getName());

    public static String encode(CharSequence password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.toString().getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, crypt.digest()).toString(16);

        } catch (Exception ex) {
            log.info("Error encode password: " + ex.getMessage());
            throw new RuntimeException("Error encode password", ex);
        }
    }
}
