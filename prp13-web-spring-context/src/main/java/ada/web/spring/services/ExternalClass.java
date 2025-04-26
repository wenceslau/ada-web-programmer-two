package ada.web.spring.services;

public class ExternalClass {

    public String getExternalMessage() {
        return "Olá eu sou uma classe externa, mas não posso ser injetada, a não se que eu seja um bean gerenciado pelo Spring";
    }
}
