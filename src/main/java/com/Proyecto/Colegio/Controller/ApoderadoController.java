/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Colegio.Controller;

import Request.RequestbuscarApoderado;
import Request.RequestcrearApoderado;
import Request.RequestlistarApoderado;
import com.Proyecto.Colegio.Entity.Apoderado;
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

/**
 *
 * @author Claudio Cruzado
 */
@RestController
@RequestMapping("/Apoderado")
public class ApoderadoController {

    @Autowired
    private ApoderadoService apoderadoService;

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
            List<Apoderado> apoderados = (List<Apoderado>) apoderadoService.existeapoderado(requ.getNdocumento());
            if (apoderados.isEmpty()) {
                apoderadoService.guardar(requ);
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


}
