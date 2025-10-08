/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;

import com.Proyecto.Colegio.Entity.TipoDocumento;
import com.Proyecto.Colegio.Response.ResponseGlobal;
import com.Proyecto.Colegio.Response.ResponseListaTipoDocumento;
import com.Proyecto.Colegio.Service.TipoDocumentoService;
import com.Proyecto.Colegio.dto.TipoDocumentoDTO;
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
@RequestMapping("/TipoDocumento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping("/Mostrar")
    public ResponseEntity<ResponseListaTipoDocumento> listarDocumentos(@RequestParam(defaultValue = "1") int estado) {
        try {
            List<TipoDocumento> documentos = (List<TipoDocumento>) tipoDocumentoService.listar(estado);
            List<?> dataList = documentos;

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
                ResponseListaTipoDocumento response = new ResponseListaTipoDocumento(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                String mensaje = "Documentos listados con éxito.";
                @SuppressWarnings("unchecked")
                List<TipoDocumentoDTO> documentosDTO = (List<TipoDocumentoDTO>) dataList;

                ResponseListaTipoDocumento response = new ResponseListaTipoDocumento(true, mensaje, HttpStatus.OK, documentosDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. Intente más tarde.";
            ResponseListaTipoDocumento response = new ResponseListaTipoDocumento(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor.";
            ResponseListaTipoDocumento response = new ResponseListaTipoDocumento(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Insertar")
    public ResponseEntity<ResponseGlobal> crearDocumento(@RequestBody TipoDocumentoDTO dto) {

        ResponseGlobal responseGlobal;

        try {
            List<TipoDocumento> documentos = (List<TipoDocumento>) tipoDocumentoService.existe(dto.getDescripcion());
            if (documentos.isEmpty()) {
                tipoDocumentoService.guardar(dto);
                String mensaje = "Tipo de Documento insertado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.CREATED);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CREATED);
            } else {
                String mensaje = "Tipo de Documento ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo insertar Tipo de Documento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo insertar Tipo de Documento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarTipo/{id}")
    public ResponseEntity<ResponseGlobal> actualizarDocumento(
            @PathVariable Integer id,
            @RequestBody TipoDocumentoDTO dto
    ) {
        ResponseGlobal responseGlobal;

        try {

            List<TipoDocumento> documentos = (List<TipoDocumento>) tipoDocumentoService.existe(dto.getDescripcion());
            if (documentos.isEmpty()) {
                tipoDocumentoService.actualizar(id, dto);
                String mensaje = "Tipo de Documento actualizado correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Tipo de Documento ya existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.CONFLICT);
                return new ResponseEntity<>(responseGlobal, HttpStatus.CONFLICT);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el Tipo de Documento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el Tipo de Documento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ActualizarEstado/{id}")
    public ResponseEntity<ResponseGlobal> actualizarEstadoDocumento(
            @PathVariable Integer id,
            @RequestParam int nuevoEstado,
            @RequestBody TipoDocumentoDTO dto
    ) {
        ResponseGlobal responseGlobal;

        try {
            List<TipoDocumento> documentos = (List<TipoDocumento>) tipoDocumentoService.existeId(id);

            if (!documentos.isEmpty()) {
                tipoDocumentoService.actualizarEstado(id, nuevoEstado);
                String estadoTexto;
                estadoTexto = switch (nuevoEstado) {
                    case 1 -> "activo";
                    case 0 -> "inactivo";
                    default -> String.valueOf(nuevoEstado);
                };
                String mensaje = "Estado del Tipo de Documento ID " + id + " actualizado a " + estadoTexto + " correctamente.";
                responseGlobal = new ResponseGlobal(true, mensaje, HttpStatus.OK);
                return new ResponseEntity<>(responseGlobal, HttpStatus.OK);
            } else {
                String mensaje = "Id " +id+" Tipo de Documento no existe";
                responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(responseGlobal, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            String mensaje = "Error al acceder a la base de datos. No se pudo actualizar el estado del Tipo de Documento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            String mensaje = "Error interno inesperado del servidor. No se pudo actualizar el estado del Tipo de Documento.";
            responseGlobal = new ResponseGlobal(false, mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(responseGlobal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
