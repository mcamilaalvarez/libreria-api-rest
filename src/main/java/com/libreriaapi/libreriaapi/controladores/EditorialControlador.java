package com.libreriaapi.libreriaapi.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libreriaapi.libreriaapi.entidades.Editorial;
import com.libreriaapi.libreriaapi.modelos.EditorialDTO;
import com.libreriaapi.libreriaapi.servicios.EditorialServicio;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @PostMapping("crear")
    public ResponseEntity<Object> crearEditorial(String nombre) {
        try {
            editorialServicio.crearEditorial(nombre);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Editorial>> listarEditoriales() {
        try {
            List<Editorial> editoriales = editorialServicio.listarEditoriales();
            return new ResponseEntity<>(editoriales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar-estado")
    public ResponseEntity<List<Editorial>> listarEditorialesPorEstado(@RequestParam String estado) {
        try {
            List<Editorial> editoriales = editorialServicio.listarEditorialesPorEstado(estado);
            return new ResponseEntity<>(editoriales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/modificar")
    public ResponseEntity<Void> modificarEditorial(@RequestParam String nombre, @RequestParam String id) {
        try {
            editorialServicio.modificarEditorial(nombre, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/desactivar-editorial")
    public ResponseEntity<Void> desactivarEditorial(@RequestParam String id) {
        try {
            editorialServicio.desactivarEditorial(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarEditorial/{id}")
    public ResponseEntity<EditorialDTO> listarEditorial(@PathVariable String id) {
        try {
            // Llamamos al servicio para obtener el DTO
            EditorialDTO editorialDTO = editorialServicio.obtenerEditorialDTO(id);
            return ResponseEntity.ok(editorialDTO);
        } catch (EntityNotFoundException e) {
            // Si no se encuentra la entidad, devolvemos un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Se puede devolver null, pero también podrías enviar un mensaje de error en el
                                 // cuerpo
        } catch (Exception e) {
            // En caso de error general, devolvemos un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
