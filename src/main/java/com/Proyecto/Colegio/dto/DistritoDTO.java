/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.dto;


/**
 *
 * @author Claudio Cruzado
 */

public class DistritoDTO {
    private String descripcion;
    private Integer idprovincia;

    public DistritoDTO() {
    }

    public DistritoDTO(String descripcion, Integer idProvincia) {
        this.descripcion = descripcion;
        this.idprovincia = idProvincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdDepartamento() {
        return idprovincia;
    }

    public void setIdDepartamento(Integer idProvincia) {
        this.idprovincia = idProvincia;
    }
    
}
