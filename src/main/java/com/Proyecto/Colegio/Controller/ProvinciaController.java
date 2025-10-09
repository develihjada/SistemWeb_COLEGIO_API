/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;

import com.Proyecto.Colegio.Entity.Provincia;
import com.Proyecto.Colegio.Response.ResponseGlobal;
import com.Proyecto.Colegio.Response.ResponseListaProvincia;
import com.Proyecto.Colegio.Service.ProvinciaService;
import com.Proyecto.Colegio.dto.ProvinciaDTO;
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
@RequestMapping("/Provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/Mostrar")
    public ResponseEntity<ResponseListaProvincia> listarProvincias(@RequestParam(defaultValue = "1") int estado) {
        try {
            List<Provincia> provincia = (List<Provincia>) provinciaService.listar(estado);
            List<?> dataList = provincia;

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
                ResponseListaProvincia response = new ResponseListaProvincia(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Provincias listadas con éxito.";
                @SuppressWarnings("unchecked")
                List<ProvinciaDTO> provinciaDTO = (List<ProvinciaDTO>) dataList;

                ResponseListaProvincia response = new ResponseListaProvincia(true, mensaje, HttpStatus.OK, provinciaDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaProvincia response = new ResponseListaProvincia(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaProvincia response = new ResponseListaProvincia(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    @PostMapping("/Insertar")
    public ResponseEntity<ResponseGlobal> crearDepartamento(@RequestBody ProvinciaDTO dto) {

        ResponseGlobal responseGlobal;

        try {
            List<Provincia> provincia = (List<Provincia>) provinciaService.existe(dto.getDescripcion());
            if (provincia.isEmpty()) {
                provinciaService.guardar(dto);
                String mensaje = "Provincia insertado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.CREATED);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CREATED);
            } else {
                String mensaje = "Provincia ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo insertar Provincia.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo insertar Provincia.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Actualizar/{id}")
    public ResponseEntity<ResponseGlobal> actualizarDocumento(
            @PathVariable Integer id,
            @RequestBody ProvinciaDTO dto
    ) {
        ResponseGlobal responseGlobal;

        try {

            List<Provincia> provincia = (List<Provincia>) provinciaService.existe(dto.getDescripcion());
            if (provincia.isEmpty()) {
                provinciaService.actualizar(id, dto);
                String mensaje = "Provincia actualizado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Provincia ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar la Provincia.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar la Provincia.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarEstado/{id}")
    public ResponseEntity<ResponseGlobal> actuializarEstadoProvincia(
            @PathVariable Integer id,
            @RequestParam int nuevoEstado,
            @RequestBody ProvinciaDTO dto
    ) {
        ResponseGlobal responseGlobal;

        try {
            List<Provincia> provincia = (List<Provincia>) provinciaService.existeId(id);

            if (!provincia.isEmpty()) {
                provinciaService.actualizarEstado(id, nuevoEstado);
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
                String mensaje = "Estado de la Provincia ID " + id + " actualizado a " + estadoTexto + " correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Id " + id + " Provincia no existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseGlobal, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el estado de la Provincia.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el estado de la Provincia.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
