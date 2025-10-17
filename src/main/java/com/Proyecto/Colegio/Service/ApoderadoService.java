/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;

import Request.RequestcrearApoderado;
import com.Proyecto.Colegio.Entity.Apoderado;
import com.Proyecto.Colegio.Repository.ApoderadoRepository;
import com.Proyecto.Colegio.dto.ApoderadoListarDTO;
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
public class ApoderadoService {
    
    @Autowired
    private ApoderadoRepository apoderadoRepository;

    @Transactional(readOnly = true)
    public List<ApoderadoListarDTO> listar(int estado) {
        return apoderadoRepository.mostrarApoderado(estado);
    }
    
    @Transactional(readOnly = true)
    public List<ApoderadoListarDTO> buscar(String ndocumento) {
        return apoderadoRepository.buscarApoderado(ndocumento);
    }
    
    public List<Apoderado> existeapoderado(String ndocumento) {
        return apoderadoRepository.existeApoderado(ndocumento);
    }
    
    public List<Apoderado> existeId(int id) {
        return apoderadoRepository.existeIdApoderado(id);
    }

    public void guardar(RequestcrearApoderado dto) {
        apoderadoRepository.insertarApoderado(dto.getNombres(), 
                                                  dto.getApellidos(),
                                                  dto.getIdtipodocumento(),
                                                  dto.getNdocumento(),
                                                  dto.getDireccion(),
                                                  dto.getIddistrito(),
                                                  dto.getTelefono(),
                                                  dto.getEmail(),
                                                  dto.getIdParentezco());
    }

    
}
