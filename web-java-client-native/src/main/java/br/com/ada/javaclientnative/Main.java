package br.com.ada.javaclientnative;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        String cep = "52050375";
        String urlString = "https://viacep.com.br/ws/" + cep + "/json";

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Resposta API via cep");
                System.out.println(response);
            }else {
                System.out.println("Erro na requisição: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception ex) {
            System.err.println("Erro ao consultar API" + ex.getMessage());
        }

    }
}
