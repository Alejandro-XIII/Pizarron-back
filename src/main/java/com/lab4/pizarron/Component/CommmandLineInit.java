package com.lab4.pizarron.Component;

import com.lab4.pizarron.model.Dibujo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommmandLineInit implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/pizarronapp/send-dibujo";

    @Override
    public void run(String... args) throws Exception {
        List<Dibujo> dibujos = new ArrayList<>();

        dibujos.add(new Dibujo("{\"lines\":[{\"points\":[{\"x\":262.76087562070404,\"y\":168.40355537642253},{\"x\":262.76087562070404,\"y\":168.40355537642253},{\"x\":262.76087562070404,\"y\":168.40355537642253}],\"brushColor\":\"black\",\"brushRadius\":1.5}],\"width\":500,\"height\":500}"));

        enviarDibujosPeriodicamente(dibujos);
    }

    private void enviarDibujosPeriodicamente(List<Dibujo> dibujos) {
        for(Dibujo dibujo : dibujos){
            enviarDibujo(dibujo);
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    private void enviarDibujo(Dibujo dibujo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Dibujo> requestEntity = new HttpEntity<>(dibujo,headers);

        ResponseEntity<Dibujo> responseEntity = restTemplate.postForEntity(url,requestEntity,Dibujo.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            System.out.println("Dibujo enviado: " + dibujo);
        } else {
            System.out.println("Error al enviar dibujo: " + dibujo);
        }
    }
}
