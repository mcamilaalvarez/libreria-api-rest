package com.libreriaapi.libreriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libreriaapi.libreriaapi.entidades.Libro;
import com.libreriaapi.libreriaapi.modelos.LibroListarActivosDTO;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long>{
    
       @Query("SELECT new com.libreriaapi.libreriaapi.modelos.LibroListarActivosDTO(l.titulo, l.ejemplares) " +
            "FROM Libro l WHERE l.libroActivo = true")
    List<LibroListarActivosDTO> encontrarActivos();
}
