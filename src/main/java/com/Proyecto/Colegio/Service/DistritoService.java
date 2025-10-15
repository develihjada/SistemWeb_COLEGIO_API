/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;


import Request.RequestactualizarDistrito;
import Request.RequestactuializarEstadoDistrito;
import Request.RequestcrearDistrito;
import com.Proyecto.Colegio.Entity.Distrito;
import com.Proyecto.Colegio.Repository.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;
/**
 *
 * @author Claudio Cruzado
 */
@Service
@Transactional
public class DistritoService {
    
    @Autowired
    private DistritoRepository distritoRepository;

    @Transactional(readOnly = true)
    public List<Distrito> listar(int estado) {
        return distritoRepository.mostrarDistrito(estado);
    }
    
    public List<Distrito> existe(String descripcion) {
        return distritoRepository.existeDistrito(descripcion);
    }
    
    public List<Distrito> existeId(int id) {
        return distritoRepository.existeIdDistrito(id);
    }

    public void guardar(RequestcrearDistrito dto) {
        distritoRepository.insertarDistrito(dto.getDescripcion(), dto.getIdProvincia());
    }

    public void actualizar(RequestactualizarDistrito dto) {
        distritoRepository.actualizarDistrito(dto.getId(), dto.getDescripcion(), dto.getIdProvincia());
    }

    public void actualizarEstado(RequestactuializarEstadoDistrito dto) {
        distritoRepository.actualizarEstadoDistrito(dto.getId(), dto.getEstado());
    }
    
    public List<Distrito> listarProvinciasDepartamento(Integer iddepartamento) {
        return distritoRepository.listarDistritoProvincias(iddepartamento);
    }    
}
