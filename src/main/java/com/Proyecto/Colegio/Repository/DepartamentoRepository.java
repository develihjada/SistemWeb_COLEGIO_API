/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Repository;


import com.Proyecto.Colegio.Entity.Departamento;
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
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    @Procedure(name = "MostrarDepartamento")
    List<Departamento> mostrarDepartamento(@Param("estado") int estado);
    
    @Procedure(name = "ExisteDepartamento")
    List<Departamento> existeDepartamento(@Param("descripcion") String descripcion);
    
    @Procedure(name = "ExisteIdDepartamento")
    List<Departamento> existeIdDepartamento(@Param("id") int id);

    @Procedure(name = "InsertarDepartamento")
    void insertarDepartamento(@Param("descripcion") String descripcion);

    @Procedure(name = "ActualizarDepartamento")
    void actualizarDepartamento(@Param("id") Integer id, @Param("descripcion") String descripcion);

    @Procedure(name = "ActualizarEstadoDepartamento")
    void actualizarEstadoDepartamento(@Param("id") Integer id, @Param("estado") int estado);
    
}
