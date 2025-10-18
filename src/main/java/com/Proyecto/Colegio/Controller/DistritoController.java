/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;

import Request.RequestactualizarDistrito;
import Request.RequestactuializarEstadoDistrito;
import Request.RequestcrearDistrito;
import Request.RequestlistarDistrito;
import Request.RequestlistarProvinciasDepartamento;
import com.Proyecto.Colegio.Entity.Distrito;
import com.Proyecto.Colegio.Response.ResponseGlobal;
import com.Proyecto.Colegio.Response.ResponseListaDistrito;
import com.Proyecto.Colegio.Service.DistritoService;
import com.Proyecto.Colegio.dto.DistritoDTO;
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
@RequestMapping("/Distrito")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @PostMapping("/Mostrar")
    public ResponseEntity<ResponseListaDistrito> listarDistrito(@RequestBody RequestlistarDistrito requ) {
        try {
            List<Distrito> distrito = (List<Distrito>) distritoService.listar(requ.getEstado());
            List<?> dataList = distrito;

            if (dataList.isEmpty()) {
                String mensaje;
                switch (requ.getEstado()) {
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
                ResponseListaDistrito response = new ResponseListaDistrito(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Distritos listados con éxito.";
                @SuppressWarnings("unchecked")
                List<DistritoDTO> distritoDTO = (List<DistritoDTO>) dataList;

                ResponseListaDistrito response = new ResponseListaDistrito(true, mensaje, HttpStatus.OK, distritoDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaDistrito response = new ResponseListaDistrito(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaDistrito response = new ResponseListaDistrito(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    @PostMapping("/Insertar")
    public ResponseEntity<ResponseGlobal> crearDistrito(@RequestBody RequestcrearDistrito requ) {

        ResponseGlobal responseGlobal;

        try {
            List<Distrito> distrito = (List<Distrito>) distritoService.existe(requ.getDescripcion());
            if (distrito.isEmpty()) {
                distritoService.guardar(requ);
                String mensaje = "Distrito insertado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.CREATED);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CREATED);
            } else {
                String mensaje = "Distrito ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo insertar Distrito.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo insertar Distrito.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<ResponseGlobal> actualizarDistrito(
            @RequestBody RequestactualizarDistrito requ
    ) {
        ResponseGlobal responseGlobal;

        try {

            List<Distrito> distrito = (List<Distrito>) distritoService.existe(requ.getDescripcion());
            if (distrito.isEmpty()) {
                distritoService.actualizar(requ);
                String mensaje = "Distrito actualizado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Distrito ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el Distrito.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el Distrito.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarEstado")
    public ResponseEntity<ResponseGlobal> actuializarEstadoDistrito(
            @RequestBody RequestactuializarEstadoDistrito requ
    ) {
        ResponseGlobal responseGlobal;

        try {
            List<Distrito> distrito = (List<Distrito>) distritoService.existeId(requ.getId());

            if (!distrito.isEmpty()) {
                distritoService.actualizarEstado(requ);
                String estadoTexto;
                switch (requ.getEstado()) {
                    case 1:
                        estadoTexto = "activo";
                        break;
                    case 0:
                        estadoTexto = "inactivo";
                        break;
                    default:
                        estadoTexto = String.valueOf(requ.getEstado());
                }
                String mensaje = "Estado del Distrito ID " + requ.getId()  + " actualizado a " + estadoTexto + " correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Id " + requ.getId() + " Distrito no existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseGlobal, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el estado del Distrito.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el estado del Distrito.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/MostrarDistritoProvincias")
    public ResponseEntity<ResponseListaDistrito> listarDistritoProvincia(@RequestBody RequestlistarProvinciasDepartamento requ) {
        try {
            List<Distrito> distrito = (List<Distrito>) distritoService.listarProvinciasDepartamento(requ.getIddepartamento());
            List<?> dataList = distrito;

            if (dataList.isEmpty()) {
                String mensaje = "No se encontró ningún elemento.";
                ResponseListaDistrito response = new ResponseListaDistrito(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Distritos listados con éxito.";
                @SuppressWarnings("unchecked")
                List<DistritoDTO> distritoDTO = (List<DistritoDTO>) dataList;

                ResponseListaDistrito response = new ResponseListaDistrito(true, mensaje, HttpStatus.OK, distritoDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaDistrito response = new ResponseListaDistrito(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaDistrito response = new ResponseListaDistrito(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
