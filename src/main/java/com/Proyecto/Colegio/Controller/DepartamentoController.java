/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;

import com.Proyecto.Colegio.Entity.Departamento;
import com.Proyecto.Colegio.Response.ResponseGlobal;
import com.Proyecto.Colegio.Response.ResponseListaDepartamento;
import com.Proyecto.Colegio.Service.DepartamentoService;
import com.Proyecto.Colegio.dto.DepartamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Claudio Cruzado
 */
@RestController
@RequestMapping("/Departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/Mostrar")
    public ResponseEntity<ResponseListaDepartamento> listarDepartamentos(@RequestParam(defaultValue = "1") int estado) {
        try {
            List<Departamento> departamento = (List<Departamento>) departamentoService.listar(estado);
            List<?> dataList = departamento;

            if (dataList.isEmpty()) {
                String mensaje;
                switch (estado) {
                    case 1:
                        mensaje = "No se encontró ningún elemento activo.";
                        break;
                    case 0:
                        mensaje = "No se encontró ningún elemento inactivo.";
                        break;
                    case 2:
                    default:
                        mensaje = "No se encontró ningún elemento.";
                        break;
                }
                ResponseListaDepartamento response = new ResponseListaDepartamento(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Departamentos listados con éxito.";
                @SuppressWarnings("unchecked")
                List<DepartamentoDTO> documentosDTO = (List<DepartamentoDTO>) dataList;

                ResponseListaDepartamento response = new ResponseListaDepartamento(true, mensaje, HttpStatus.OK, documentosDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaDepartamento response = new ResponseListaDepartamento(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaDepartamento response = new ResponseListaDepartamento(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Insertar")
    public ResponseEntity<ResponseGlobal> crearDepartamento(@RequestBody DepartamentoDTO dto) {

        ResponseGlobal responseGlobal;

        try {
            List<Departamento> departamento = (List<Departamento>) departamentoService.existe(dto.getDescripcion());
            if (departamento.isEmpty()) {
                departamentoService.guardar(dto);
                String mensaje = "Departamento insertado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.CREATED);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CREATED);
            } else {
                String mensaje = "Departamento ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo insertar Departamento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo insertar Departamento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarTipo/{id}")
    public ResponseEntity<ResponseGlobal> actualizarDocumento(
            @PathVariable Integer id,
            @RequestBody DepartamentoDTO dto
    ) {
        ResponseGlobal responseGlobal;

        try {

            List<Departamento> departamento = (List<Departamento>) departamentoService.existe(dto.getDescripcion());
            if (departamento.isEmpty()) {
                departamentoService.actualizar(id, dto);
                String mensaje = "Departamento actualizado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Departamento ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar Departamento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el Departamento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarEstado/{id}")
    public ResponseEntity<ResponseGlobal> actualizarEstadoDepartamento(
            @PathVariable Integer id,
            @RequestParam int nuevoEstado,
            @RequestBody DepartamentoDTO dto
    ) {
        ResponseGlobal responseGlobal;

        try {
            List<Departamento> departamento = (List<Departamento>) departamentoService.existeId(id);

            if (!departamento.isEmpty()) {
                departamentoService.actualizarEstado(id, nuevoEstado);
                String estadoTexto;
                switch (nuevoEstado) {
                    case 1:
                        estadoTexto = "activo";
                        break;
                    case 0:
                        estadoTexto = "inactivo";
                        break;
                    default:
                        estadoTexto = String.valueOf(nuevoEstado);
                }
                String mensaje = "Estado del Departamento ID " + id + " actualizado a " + estadoTexto + " correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Id " + id + " Departamento no existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseGlobal, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el estado del Departamento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el estado del Departamento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
