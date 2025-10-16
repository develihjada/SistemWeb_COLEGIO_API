/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Response;

import com.Proyecto.Colegio.dto.ParentezcoDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author Claudio Cruzado
 */
@Data
public class ResponseListaParentezco extends ResponseGlobal {
    
    private List<ParentezcoDTO> parentezco;

    public ResponseListaParentezco(boolean exito, String mensaje, HttpStatus httpStatus, List<ParentezcoDTO> parentezco) {
        super(exito, mensaje, httpStatus);
        this.parentezco = parentezco;
    }

    public ResponseListaParentezco(boolean exito, String mensaje, HttpStatus httpStatus) {
        super(exito, mensaje, httpStatus);
        this.parentezco = Collections.emptyList();
    }
}
