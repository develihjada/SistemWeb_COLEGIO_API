/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;

import Request.RequestactualizarDepartamento;
import Request.RequestcrearDepartamento;
import com.Proyecto.Colegio.Entity.Departamento;
import com.Proyecto.Colegio.Repository.DepartamentoRepository;
import com.Proyecto.Colegio.dto.DepartamentoDTO;
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
public class DepartamentoService {
    
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional(readOnly = true)
    public List<Departamento> listar(int estado) {
        return departamentoRepository.mostrarDepartamento(estado);
    }
    
    public List<Departamento> existe(String descripcion) {
        return departamentoRepository.existeDepartamento(descripcion);
    }
    
    public List<Departamento> existeId(int id) {
        return departamentoRepository.existeIdDepartamento(id);
    }

    public void guardar(RequestcrearDepartamento dto) {
        departamentoRepository.insertarDepartamento(dto.getDescripcion());
    }

    public void actualizar(RequestactualizarDepartamento requ) {
        departamentoRepository.actualizarDepartamento(requ.getId(), requ.getDescripcion());
    }

    public void actualizarEstado(Integer id, int nuevoEstado) {
        departamentoRepository.actualizarEstadoDepartamento(id, nuevoEstado);
    }
    
}
