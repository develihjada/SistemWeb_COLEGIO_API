/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;


import Request.RequestactualizarProvincia;
import Request.RequestactuializarEstadoProvincia;
import Request.RequestcrearProvincia;
import com.Proyecto.Colegio.Entity.Provincia;
import com.Proyecto.Colegio.Repository.ProvinciaRepository;
import com.Proyecto.Colegio.dto.ProvinciaDTO;
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
public class ProvinciaService {
    
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Transactional(readOnly = true)
    public List<Provincia> listar(int estado) {
        return provinciaRepository.mostrarProvincia(estado);
    }
    
    public List<Provincia> existe(String descripcion) {
        return provinciaRepository.existeProvincia(descripcion);
    }
    
    public List<Provincia> existeId(int id) {
        return provinciaRepository.existeIdProvincia(id);
    }

    public void guardar(RequestcrearProvincia dto) {
        provinciaRepository.insertarProvincia(dto.getDescripcion(), dto.getIdDepartamento());
    }

    public void actualizar(RequestactualizarProvincia dto) {
        provinciaRepository.actualizarProvincia(dto.getId(), dto.getDescripcion(), dto.getIdDepartamento());
    }

    public void actualizarEstado(RequestactuializarEstadoProvincia dto) {
        provinciaRepository.actualizarEstadoProvincia(dto.getId(), dto.getEstado());
    }
    
    public List<Provincia> listarProvinciasDepartamento(Integer iddepartamento) {
        return provinciaRepository.listarProvinciasDepartamento(iddepartamento);
    }    
}
