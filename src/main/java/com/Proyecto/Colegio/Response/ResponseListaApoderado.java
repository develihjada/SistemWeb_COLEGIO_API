/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Response;

import com.Proyecto.Colegio.dto.ApoderadoListarDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author Claudio Cruzado
 */
@Data
public class ResponseListaApoderado extends ResponseGlobal {
    
    private List<ApoderadoListarDTO> apoderado;

    public ResponseListaApoderado(boolean exito, String mensaje, HttpStatus httpStatus, List<ApoderadoListarDTO> apoderado) {
        super(exito, mensaje, httpStatus);
        this.apoderado = apoderado;
    }

    public ResponseListaApoderado(boolean exito, String mensaje, HttpStatus httpStatus) {
        super(exito, mensaje, httpStatus);
        this.apoderado = Collections.emptyList();
    }
}
