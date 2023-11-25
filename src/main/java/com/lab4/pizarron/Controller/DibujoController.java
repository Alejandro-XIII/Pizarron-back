package com.lab4.pizarron.Controller;

import com.lab4.pizarron.model.Dibujo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class DibujoController {

    @MessageMapping("/pizarron")
    @SendTo("/pizarron/dibujo")
    public Dibujo envio(Dibujo dibujo){
        return dibujo;
    }
}