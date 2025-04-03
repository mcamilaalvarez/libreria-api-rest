package com.libreriaapi.libreriaapi.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaapi.libreriaapi.entidades.Autor;
import com.libreriaapi.libreriaapi.entidades.Editorial;
import com.libreriaapi.libreriaapi.entidades.Libro;
import com.libreriaapi.libreriaapi.modelos.LibroCreateDTO;
import com.libreriaapi.libreriaapi.modelos.LibroDTO;
import com.libreriaapi.libreriaapi.modelos.LibroListarActivosDTO;
import com.libreriaapi.libreriaapi.repositorios.LibroRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @Transactional
    public void crearLibro(LibroCreateDTO libroCreateDTO) {
        Libro libroNvo = new Libro();
        libroNvo.setId(libroCreateDTO.getIsbn());
        libroNvo.setTitulo(libroCreateDTO.getTitulo());
        libroNvo.setEjemplares(libroCreateDTO.getEjemplares());
        libroNvo.setLibroActivo(libroCreateDTO.isLibroActivo());
        Autor autor = autorServicio.getOne(libroCreateDTO.getIdAutor());
        if (autor != null) {
            libroNvo.setAutor(autor);
        }
        Editorial editorial = editorialServicio.getOne(libroCreateDTO.getIdEditorial());
        if (editorial != null) {
            libroNvo.setEditorial(editorial);
        }
        libroRepositorio.save(libroNvo);
    }

    @Transactional(readOnly = true)
    public List<LibroListarActivosDTO> listarLibrosActivos() {
        List <LibroListarActivosDTO>  libros = new ArrayList<>();
        libros = libroRepositorio.encontrarActivos();
        return libros;
    }

    @Transactional (readOnly = true)
    public LibroDTO obtenerLibroDTO(Long id) {
        Optional <Libro> optionalLibro = libroRepositorio.findById(id);
        Libro libro = null;
        try {
            if(optionalLibro.isPresent()){
                libro = optionalLibro.get();
            }else {
                throw new EntityNotFoundException("No se encontró el libro con el id: " + id);

            }
        } catch (Exception e) {
            throw e;
        }
        return new LibroDTO(libro.getId(), libro.getTitulo(),libro.getEjemplares());
    }
}
