package com.lab4.pizarron.Controller;

import com.lab4.pizarron.model.Dibujo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizarronapp")
public class PizarronController {

    private final SimpMessagingTemplate template;

    public PizarronController(SimpMessagingTemplate template){
        this.template = template;
    }

    @PostMapping("/send-dibujo")
    public void sendDibujo(@RequestBody Dibujo dibujo){
        this.template.convertAndSend("/pizarron/dibujo", dibujo);
    }
}
