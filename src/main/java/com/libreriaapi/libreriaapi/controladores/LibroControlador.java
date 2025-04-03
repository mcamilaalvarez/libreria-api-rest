package com.libreriaapi.libreriaapi.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libreriaapi.libreriaapi.modelos.LibroCreateDTO;
import com.libreriaapi.libreriaapi.modelos.LibroDTO;
import com.libreriaapi.libreriaapi.modelos.LibroListarActivosDTO;
import com.libreriaapi.libreriaapi.servicios.LibroServicio;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio libroServicio;

    @PostMapping("crear")
    public ResponseEntity<Object> crearLibro(@RequestBody LibroCreateDTO libroDTO){
         try {
            libroServicio.crearLibro(libroDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"Algun dato no es correcto o es nulo, revisar.\"}");
        }
    }

     @GetMapping("/listarLibrosActivos")
    public ResponseEntity<List <LibroListarActivosDTO>> listarLibros() {
        try {
            List<LibroListarActivosDTO> librosActivos = libroServicio.listarLibrosActivos();
            return ResponseEntity.ok(librosActivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarLibro/{id}")
    public ResponseEntity<LibroDTO> listarLibro ( @PathVariable Long id){
        try {
            LibroDTO libroDTO = libroServicio.obtenerLibroDTO(id);
            return ResponseEntity.ok(libroDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();        }
    }
}
