/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;

import Request.RequestactualizarParentezco;
import Request.RequestcrearParentezco;
import com.Proyecto.Colegio.Entity.Parentezco;
import com.Proyecto.Colegio.Repository.ParentezcoRepository;
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
public class ParentezcoService {
    
    @Autowired
    private ParentezcoRepository parentezcoRepository;

    @Transactional(readOnly = true)
    public List<Parentezco> listar(int estado) {
        return parentezcoRepository.mostrarParentezco(estado);
    }
    
    public List<Parentezco> existe(String descripcion) {
        return parentezcoRepository.existeParentezco(descripcion);
    }
    
    public List<Parentezco> existeId(int id) {
        return parentezcoRepository.existeIdParentezco(id);
    }

    public void guardar(RequestcrearParentezco dto) {
        parentezcoRepository.insertarTipoParentezco(dto.getDescripcion());
    }

    public void actualizar(RequestactualizarParentezco dto) {
        parentezcoRepository.actualizarParentezco(dto.getId(), dto.getDescripcion());
    }

    public void actualizarEstado(Integer id, int nuevoEstado) {
        parentezcoRepository.actualizarEstadoParentezco(id, nuevoEstado);
    }
    
}
