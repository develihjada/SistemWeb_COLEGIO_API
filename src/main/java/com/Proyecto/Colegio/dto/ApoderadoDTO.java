/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.dto;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Claudio Cruzado
 */
@Setter
@Getter
public class ApoderadoDTO {
    private String nombres;
    private String apellidos;
    private int idtipodocumento;
    private String ndocumento;
    private String direccion;
    private int iddistrito;
    private String telefono;
    private String email;
    private int idparentezco;
    
    public ApoderadoDTO() {
    }

    
}
