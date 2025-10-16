/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;


import Request.RequestactualizarEstadoDocumento;
import Request.RequestactualizarEstadoParentezco;
import Request.RequestactualizarParentezco;
import Request.RequestcrearParentezco;
import Request.RequestlistarParentezco;
import com.Proyecto.Colegio.Entity.Parentezco;
import com.Proyecto.Colegio.Response.ResponseGlobal;
import com.Proyecto.Colegio.Response.ResponseListaParentezco;
import com.Proyecto.Colegio.Service.ParentezcoService;
import com.Proyecto.Colegio.dto.ParentezcoDTO;
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
@RequestMapping("/Parentezco")
public class ParentezcoController {

    @Autowired
    private ParentezcoService parentezcoService;

    @PostMapping("/Mostrar")
    public ResponseEntity<ResponseListaParentezco> listarParentezco(@RequestBody RequestlistarParentezco requ) {
        try {
            List<Parentezco> documentos = (List<Parentezco>) parentezcoService.listar(requ.getEstado());
            List<?> dataList = documentos;

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
                ResponseListaParentezco response = new ResponseListaParentezco(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Parentezco listados con éxito.";
                @SuppressWarnings("unchecked")
                List<ParentezcoDTO> parentezcoDTO = (List<ParentezcoDTO>) dataList;

                ResponseListaParentezco response = new ResponseListaParentezco(true, mensaje, HttpStatus.OK, parentezcoDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaParentezco response = new ResponseListaParentezco(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaParentezco response = new ResponseListaParentezco(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Insertar")
    public ResponseEntity<ResponseGlobal> crearDocumento(@RequestBody RequestcrearParentezco requ) {

        ResponseGlobal responseGlobal;

        try {
            List<Parentezco> parentezco = (List<Parentezco>) parentezcoService.existe(requ.getDescripcion());
            if (parentezco.isEmpty()) {
                parentezcoService.guardar(requ);
                String mensaje = "Parentezco insertado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.CREATED);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CREATED);
            } else {
                String mensaje = "Parentezco ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo insertar Parentezco.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo insertar Parentezco.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<ResponseGlobal> actualizarDocumento(
            @RequestBody RequestactualizarParentezco requ
    ) {
        ResponseGlobal responseGlobal;

        try {

            List<Parentezco> parentezco = (List<Parentezco>) parentezcoService.existe(requ.getDescripcion());
            if (parentezco.isEmpty()) {
                parentezcoService.actualizar(requ);
                String mensaje = "Parentezco actualizado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Parentezco ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar Parentezco.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar Parentezco.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarEstado")
    public ResponseEntity<ResponseGlobal> actualizarEstadoDocumento( 
            @RequestBody  RequestactualizarEstadoParentezco requ
    ) {
        ResponseGlobal responseGlobal;

        try {
            List<Parentezco> parentezco = (List<Parentezco>) parentezcoService.existeId(requ.getId());

            if (!parentezco.isEmpty()) {
                parentezcoService.actualizarEstado(requ.getId(), requ.getEstado());
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
                String mensaje = "Estado de Parentezco ID " + requ.getId() + " actualizado a " + estadoTexto + " correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Id " + requ.getId() + " Parentezco no existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseGlobal, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el estado del Parentezco.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el estado del Parentezco.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
