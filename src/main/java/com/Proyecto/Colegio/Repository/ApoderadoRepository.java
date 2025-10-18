/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Repository;

import com.Proyecto.Colegio.Entity.Apoderado;
import com.Proyecto.Colegio.dto.ApoderadoListarDTO;
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
public interface ApoderadoRepository extends JpaRepository<Apoderado, Integer> {

    @Procedure(name = "MostrarApoderado")
    List<ApoderadoListarDTO> mostrarApoderado(@Param("estado") int estado);

    @Procedure(name = "BuscarApoderado")
    List<ApoderadoListarDTO> buscarApoderado(@Param("ndocumento") String ndocumento);

    @Procedure(name = "ExistApoderado")
    List<Apoderado> existeApoderado(@Param("ndocumento") String ndocumento);

    @Procedure(name = "ExisteIdApoderado")
    List<Apoderado> existeIdApoderado(@Param("id") int id);

    @Procedure(name = "InsertarApoderado")
    void insertarApoderado(@Param("nombres") String nombres,
            @Param("apellidos") String apellidos,
            @Param("idtipodocumento") int idtipodocumento,
            @Param("ndocumento") String ndocumento,
            @Param("direccion") String direccion,
            @Param("iddistrito") int iddistrito,
            @Param("telefono") String telefono,
            @Param("email") String email,
            @Param("idparentezco") int idparentezco);

    @Procedure(name = "ActualizarApoderado")
    void actualizarApoderado(@Param("id") int id,
            @Param("nombres") String nombres,
            @Param("apellidos") String apellidos,
            @Param("idtipodocumento") int idtipodocumento,
            @Param("ndocumento") String ndocumento,
            @Param("direccion") String direccion,
            @Param("iddistrito") int iddistrito,
            @Param("telefono") String telefono,
            @Param("email") String email,
            @Param("idparentezco") int idparentezco);

    @Procedure(name = "ActualizarEstadoApoderado")
    void actualizarEstadoApoderado(@Param("id") Integer id, @Param("estado") int estado);

}
