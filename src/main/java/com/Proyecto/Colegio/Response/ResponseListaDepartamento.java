/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Response;


import com.Proyecto.Colegio.dto.DepartamentoDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author Claudio Cruzado
 */
@Data
public class ResponseListaDepartamento extends ResponseGlobal {
    
    private List<DepartamentoDTO> departamento;

    public ResponseListaDepartamento(boolean exito, String mensaje, HttpStatus httpStatus, List<DepartamentoDTO> departamento) {
        super(exito, mensaje, httpStatus);
        this.departamento = departamento;
    }

    public ResponseListaDepartamento(boolean exito, String mensaje, HttpStatus httpStatus) {
        super(exito, mensaje, httpStatus);
        this.departamento = Collections.emptyList();
    }
}
