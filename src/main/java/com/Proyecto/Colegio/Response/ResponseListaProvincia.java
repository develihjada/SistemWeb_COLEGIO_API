/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Response;


import com.Proyecto.Colegio.dto.ProvinciaDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author Claudio Cruzado
 */
@Data
public class ResponseListaProvincia extends ResponseGlobal {
    
    private List<ProvinciaDTO> provincia;

    public ResponseListaProvincia(boolean exito, String mensaje, HttpStatus httpStatus, List<ProvinciaDTO> provincia) {
        super(exito, mensaje, httpStatus);
        this.provincia = provincia;
    }

    public ResponseListaProvincia(boolean exito, String mensaje, HttpStatus httpStatus) {
        super(exito, mensaje, httpStatus);
        this.provincia = Collections.emptyList();
    }
}
