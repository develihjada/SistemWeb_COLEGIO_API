/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Service;

import Request.RequestactualizarDocumento;
import Request.RequestcrearDocumento;
import com.Proyecto.Colegio.Entity.TipoDocumento;
import com.Proyecto.Colegio.Repository.TipoDocumentoRepository;
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
public class TipoDocumentoService {
    
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Transactional(readOnly = true)
    public List<TipoDocumento> listar(int estado) {
        return tipoDocumentoRepository.mostrarTipoDocumento(estado);
    }
    
    public List<TipoDocumento> existe(String descripcion) {
        return tipoDocumentoRepository.existeTipoDocumento(descripcion);
    }
    
    public List<TipoDocumento> existeId(int id) {
        return tipoDocumentoRepository.existeIdTipoDocumento(id);
    }

    public void guardar(RequestcrearDocumento dto) {
        tipoDocumentoRepository.insertarTipoDocumento(dto.getDescripcion());
    }

    public void actualizar(RequestactualizarDocumento dto) {
        tipoDocumentoRepository.actualizarTipoDocumento(dto.getId(), dto.getDescripcion());
    }

    public void actualizarEstado(Integer id, int nuevoEstado) {
        tipoDocumentoRepository.actualizarEstadoTipoDocumento(id, nuevoEstado);
    }
    
}
