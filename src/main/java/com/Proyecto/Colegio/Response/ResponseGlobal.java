/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Claudio Cruzado
 */
@Data
public class ResponseGlobal {
    private boolean exito;
    private String mensaje;
    private int codigo;

    public ResponseGlobal() {}

    public ResponseGlobal(boolean exito, String mensaje, HttpStatus httpStatus) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.codigo = httpStatus.value();
    }
}
