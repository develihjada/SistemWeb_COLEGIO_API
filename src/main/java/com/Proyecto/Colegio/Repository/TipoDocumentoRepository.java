/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Repository;


import com.Proyecto.Colegio.Entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Claudio Cruzado
 */
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

    @Procedure(name = "MostrarTipoDocumento")
    List<TipoDocumento> mostrarTipoDocumento(@Param("estado") int estado);
    
    @Procedure(name = "ExisteTipoDocumento")
    List<TipoDocumento> existeTipoDocumento(@Param("descripcion") String descripcion);
    
    @Procedure(name = "ExisteIdTipoDocumento")
    List<TipoDocumento> existeIdTipoDocumento(@Param("id") int id);

    @Procedure(name = "InsertarTipoDocumento")
    void insertarTipoDocumento(@Param("descripcion") String descripcion);

    @Procedure(name = "ActualizarTipoDocumento")
    void actualizarTipoDocumento(@Param("id") Integer id, @Param("descripcion") String descripcion);

    @Procedure(name = "ActualizarEstadoTipoDocumento")
    void actualizarEstadoTipoDocumento(@Param("id") Integer id, @Param("estado") int estado);
    
}
