/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Repository;


import com.Proyecto.Colegio.Entity.Provincia;
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
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    @Procedure(name = "MostrarProvincia")
    List<Provincia> mostrarProvincia(@Param("estado") int estado);
    
    @Procedure(name = "ExisteProvincia")
    List<Provincia> existeProvincia(@Param("descripcion") String descripcion);
    
    @Procedure(name = "ExisteIdProvincia")
    List<Provincia> existeIdProvincia(@Param("id") int id);

    @Procedure(name = "InsertarProvincia")
    void insertarProvincia(@Param("descripcion") String descripcion, @Param("idDepartamento") int idDepartamento);

    @Procedure(name = "ActualizarProvincia")
    void actualizarProvincia(@Param("id") Integer id, @Param("descripcion") String descripcion, @Param("idDepartamento") int idDepartamento);

    @Procedure(name = "ActualizarEstadoProvincia")
    void actualizarEstadoProvincia(@Param("id") Integer id, @Param("estado") int estado);
    
}

