/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.dto;


/**
 *
 * @author Claudio Cruzado
 */

public class ProvinciaDTO {
    private String descripcion;
    private Integer iddepartamento;

    public ProvinciaDTO() {
    }

    public ProvinciaDTO(String descripcion, Integer idDepartamento) {
        this.descripcion = descripcion;
        this.iddepartamento = idDepartamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdDepartamento() {
        return iddepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.iddepartamento = idDepartamento;
    }
    
}
