/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Response;

import com.Proyecto.Colegio.dto.TipoDocumentoDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author Claudio Cruzado
 */
@Data
public class ResponseListaTipoDocumento extends ResponseGlobal {
    
    private List<TipoDocumentoDTO> tipoDocumento;

    public ResponseListaTipoDocumento(boolean exito, String mensaje, HttpStatus httpStatus, List<TipoDocumentoDTO> tipoDocumento) {
        super(exito, mensaje, httpStatus);
        this.tipoDocumento = tipoDocumento;
    }

    public ResponseListaTipoDocumento(boolean exito, String mensaje, HttpStatus httpStatus) {
        super(exito, mensaje, httpStatus);
        this.tipoDocumento = Collections.emptyList();
    }
}
