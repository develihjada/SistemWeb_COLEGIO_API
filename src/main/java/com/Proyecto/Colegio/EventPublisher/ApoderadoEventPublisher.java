/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.EventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Claudio Cruzado
 */
@Component
public class ApoderadoEventPublisher {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notificarCambioApoderados(String mensaje) {
        messagingTemplate.convertAndSend("/topic/apoderados", mensaje);
    }
}
