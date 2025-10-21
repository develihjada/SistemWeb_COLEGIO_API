/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;

import Request.RequestactualizarApoderado;
import Request.RequestactuializarEstadoApoderado;
import Request.RequestbuscarApoderado;
import Request.RequestcrearApoderado;
import Request.RequestlistarApoderado;
import com.Proyecto.Colegio.Entity.Apoderado;
import com.Proyecto.Colegio.EventPublisher.ApoderadoEventPublisher;
import com.Proyecto.Colegio.Response.ResponseGlobal;
import com.Proyecto.Colegio.Response.ResponseListaApoderado;
import com.Proyecto.Colegio.Service.ApoderadoService;
import com.Proyecto.Colegio.dto.ApoderadoListarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author Claudio Cruzado
 */
@RestController
@RequestMapping("/Apoderado")
@CrossOrigin(origins="*")
public class ApoderadoController {

    @Autowired
    private ApoderadoService apoderadoService;
    @Autowired
    private ApoderadoEventPublisher publisher;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @PostMapping("/Mostrar")
    public ResponseEntity<ResponseListaApoderado> listarApoderados(@RequestBody RequestlistarApoderado requ) {
        try {
            List<ApoderadoListarDTO> apoderados = (List<ApoderadoListarDTO>) apoderadoService.listar(requ.getEstado());

            if (apoderados.isEmpty()) {
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
                ResponseListaApoderado response = new ResponseListaApoderado(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Apoderados listados con éxito.";
                @SuppressWarnings("unchecked")

                ResponseListaApoderado response = new ResponseListaApoderado(true, mensaje, HttpStatus.OK, apoderados);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaApoderado response = new ResponseListaApoderado(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaApoderado response = new ResponseListaApoderado(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/Buscar")
    public ResponseEntity<ResponseListaApoderado> buscarApoderado(@RequestBody RequestbuscarApoderado requ) {
        try {
            List<ApoderadoListarDTO> apoderados = (List<ApoderadoListarDTO>) apoderadoService.buscar(requ.getNdocumento());

            if (apoderados.isEmpty()) {
                String mensaje = "No se encontró ningún elemento.";
                ResponseListaApoderado response = new ResponseListaApoderado(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Apoderado encontrado.";
                @SuppressWarnings("unchecked")

                ResponseListaApoderado response = new ResponseListaApoderado(true, mensaje, HttpStatus.OK, apoderados);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaApoderado response = new ResponseListaApoderado(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaApoderado response = new ResponseListaApoderado(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Insertar")
    public ResponseEntity<ResponseGlobal> crearDocumento(@RequestBody RequestcrearApoderado requ) {

        ResponseGlobal responseGlobal;

        try {
            Integer id = null;
            List<Apoderado> apoderados = (List<Apoderado>) apoderadoService.existeapoderado(requ.getNdocumento(), id);
            if (apoderados.isEmpty()) {
                apoderadoService.guardar(requ);  
                messagingTemplate.convertAndSend("/topic/apoderados", requ);

                String mensaje = "Apoderado insertado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.CREATED);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CREATED);
            } else {
                String mensaje = "Apoderado ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo insertar Apoderado.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo insertar Apoderado.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/Actualizar")
    public ResponseEntity<ResponseGlobal> actualizarApoderado(
            @RequestBody RequestactualizarApoderado requ
    ) {
        ResponseGlobal responseGlobal;

        try {

            List<Apoderado> apoderado = (List<Apoderado>) apoderadoService.existeapoderado(requ.getNdocumento(), requ.getId());
            if (apoderado.isEmpty()) {
                apoderadoService.actualizar(requ);
                String mensaje = "Apoderado actualizado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Apoderado ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar Apoderado.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar Apoderado.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/ActualizarEstado")
    public ResponseEntity<ResponseGlobal> actuializarEstadoApoderado(
            @RequestBody RequestactuializarEstadoApoderado requ
    ) {
        ResponseGlobal responseGlobal;

        try {
            List<Apoderado> apoderado = (List<Apoderado>) apoderadoService.existeId(requ.getId());

            if (!apoderado.isEmpty()) {
                apoderadoService.actualizarEstado(requ);
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
                String mensaje = "Estado del Apoderado ID " + requ.getId()  + " actualizado a " + estadoTexto + " correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Id " + requ.getId() + " Apoderado no existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseGlobal, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el estado del Apoderado.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el estado del Apoderado.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
