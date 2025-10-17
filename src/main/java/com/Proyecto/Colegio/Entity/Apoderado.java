/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Entity;

import com.Proyecto.Colegio.dto.ApoderadoListarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Claudio Cruzado
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQuery(
    name = "MostrarApoderado",
    procedureName = "MostrarApoderado",
    resultSetMappings = "MappingApoderadoListarDTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado", type = Integer.class)
    }
)

@NamedStoredProcedureQuery(
    name = "BuscarApoderado",
    procedureName = "BuscarApoderado",
    resultSetMappings = "MappingApoderadoListarDTO",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ndocumento", type = String.class)
    }
)
@SqlResultSetMapping(
    name = "MappingApoderadoListarDTO",
    classes = {
        @ConstructorResult(
            targetClass = ApoderadoListarDTO.class,
            columns = {
                @ColumnResult(name = "id", type = Integer.class),
                @ColumnResult(name = "nombres", type = String.class),
                @ColumnResult(name = "apellidos", type = String.class),
                @ColumnResult(name = "tipodocumento", type = String.class),
                @ColumnResult(name = "ndocumento", type = String.class),
                @ColumnResult(name = "direccion", type = String.class),
                @ColumnResult(name = "distrito", type = String.class),
                @ColumnResult(name = "provincia", type = String.class),
                @ColumnResult(name = "departamento", type = String.class),
                @ColumnResult(name = "telefono", type = String.class),
                @ColumnResult(name = "email", type = String.class),
                @ColumnResult(name = "parentezco", type = String.class),
                @ColumnResult(name = "estado", type = Integer.class)
            }
        )
    }
)

@Entity
@Table(name = "Apoderado")
public class Apoderado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombres")
    private String nombres;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "idtipodocumento")
    private int idtipodocumento;
    
    @Column(name = "ndocumento")
    private String ndocumento;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "iddistrito")
    private int iddistrito;

    @Column(name = "estado")
    private int estado;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "idparentezco")
    private int idparentezco;
    
}
