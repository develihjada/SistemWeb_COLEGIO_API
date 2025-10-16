/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Repository;


import com.Proyecto.Colegio.Entity.Parentezco;
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
public interface ParentezcoRepository extends JpaRepository<Parentezco, Integer> {

    @Procedure(name = "MostrarParentezco")
    List<Parentezco> mostrarParentezco(@Param("estado") int estado);
    
    @Procedure(name = "ExisteParentezco")
    List<Parentezco> existeParentezco(@Param("descripcion") String descripcion);
    
    @Procedure(name = "ExisteIdParentezco")
    List<Parentezco> existeIdParentezco(@Param("id") int id);

    @Procedure(name = "InsertarParentezco")
    void insertarTipoParentezco(@Param("descripcion") String descripcion);

    @Procedure(name = "ActualizarParentezco")
    void actualizarParentezco(@Param("id") Integer id, @Param("descripcion") String descripcion);

    @Procedure(name = "AtualizarEstadoParentezco")
    void actualizarEstadoParentezco(@Param("id") Integer id, @Param("estado") int estado);
    
}
