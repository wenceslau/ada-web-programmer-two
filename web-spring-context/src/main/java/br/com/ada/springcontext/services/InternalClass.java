package br.com.ada.springcontext.services;

import org.springframework.stereotype.Service;

@Service
public class InternalClass {

    public String getInternalMessage() {
        return "Olá eu sou uma classe interna, e posso ser intejtada em qualquer lugar do projeto";
    }
}
