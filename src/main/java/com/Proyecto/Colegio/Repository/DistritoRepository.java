/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Repository;

import com.Proyecto.Colegio.Entity.Distrito;
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
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {

    @Procedure(name = "MostrarDistrito")
    List<Distrito> mostrarDistrito(@Param("estado") int estado);

    @Procedure(name = "ExisteDistrito")
    List<Distrito> existeDistrito(@Param("descripcion") String descripcion);

    @Procedure(name = "ExisteIdDistrito")
    List<Distrito> existeIdDistrito(@Param("id") int id);

    @Procedure(name = "InsertarDistrito")
    void insertarDistrito(@Param("descripcion") String descripcion, @Param("idProvincia") int idDepartamento);

    @Procedure(name = "ActualizarDistrito")
    void actualizarDistrito(@Param("id") Integer id, @Param("descripcion") String descripcion, @Param("idProvincia") int idDepartamento);

    @Procedure(name = "ActualizarEstadoDistrito")
    void actualizarEstadoDistrito(@Param("id") Integer id, @Param("estado") int estado);

    @Procedure(name = "ListarDistritoProvincias")
    List<Distrito> listarDistritoProvincias(@Param("idprovincia") int iddepartamento);

}
