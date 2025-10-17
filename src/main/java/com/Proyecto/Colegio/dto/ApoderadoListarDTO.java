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
public class ApoderadoListarDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String tipodocumento;
    private String ndocumento;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String telefono;
    private String email;
    private String parentezco;
    private int estado;
    
    public ApoderadoListarDTO() {
    }
   
}
