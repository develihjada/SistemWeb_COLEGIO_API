/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;

import Request.RequestactualizarApoderado;
import Request.RequestactuializarEstadoApoderado;
import Request.RequestcrearApoderado;
import com.Proyecto.Colegio.Entity.Apoderado;
import com.Proyecto.Colegio.EventPublisher.ApoderadoEventPublisher;
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

    @Autowired
    private ApoderadoEventPublisher publisher;

    @Transactional(readOnly = true)
    public List<ApoderadoListarDTO> listar(int estado) {
        return apoderadoRepository.mostrarApoderado(estado);
    }

    @Transactional(readOnly = true)
    public List<ApoderadoListarDTO> buscar(String ndocumento) {
        return apoderadoRepository.buscarApoderado(ndocumento);
    }

    public List<Apoderado> existeapoderado(String ndocumento, Integer id) {
        return apoderadoRepository.existeApoderado(ndocumento, id);
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
        publisher.notificarCambioApoderados("nuevo_apoderado");
    }

    public void actualizar(RequestactualizarApoderado dto) {
        apoderadoRepository.actualizarApoderado(dto.getId(),
                dto.getNombres(),
                dto.getApellidos(),
                dto.getIdtipodocumento(),
                dto.getNdocumento(),
                dto.getDireccion(),
                dto.getIddistrito(),
                dto.getTelefono(),
                dto.getEmail(),
                dto.getIdParentezco());
    }

    public void actualizarEstado(RequestactuializarEstadoApoderado dto) {
        apoderadoRepository.actualizarEstadoApoderado(dto.getId(), dto.getEstado());
    }

}
