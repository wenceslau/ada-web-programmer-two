package br.com.ada;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {

        String url = "http://localhost:8080/viacep/01001000";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            try (CloseableHttpResponse response = client.execute(request)) {
                System.out.println("HttpCode: " +response.getStatusLine().getStatusCode());

                String jsonResponse = EntityUtils.toString(response.getEntity());
                System.out.println(jsonResponse);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Endereco endereco = objectMapper.readValue(jsonResponse, Endereco.class);

                System.out.println(endereco.getBairro());
                System.out.println(endereco.getLogradouro());
                System.out.println(endereco.getLocalidade());

            }


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}