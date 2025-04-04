package com.libreriaapi.libreriaapi.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaapi.libreriaapi.entidades.Autor;
import com.libreriaapi.libreriaapi.entidades.Editorial;
import com.libreriaapi.libreriaapi.modelos.EditorialDTO;
import com.libreriaapi.libreriaapi.repositorios.EditorialRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial(nombre);
        editorial.setEditorialActiva(true);
        editorialRepositorio.save(editorial);
    }

    @Transactional (readOnly = true)
    public List<Editorial> listarEditoriales() {
       List<Editorial> editoriales = new ArrayList<>();
       editoriales = editorialRepositorio.findAll();
       return editoriales;
    }

    @Transactional (readOnly = true)
    public List<Editorial> listarEditorialesPorEstado(String estado){
        List<Editorial> editoriales = new ArrayList<>();
        boolean estadoQuery= true;
        switch (estado.trim().toLowerCase()) {
            case "activa":
                estadoQuery = true;
                break;
            case "inactiva":
                estadoQuery = false;
                break;
            default:
                throw new IllegalArgumentException("El estado debe ser 'activa' o 'inactiva'");
        }
        editoriales = editorialRepositorio.listarPorEstado(estadoQuery);
        return editoriales;
    }

    @Transactional 
    public void modificarEditorial(String nombre, String id){
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombreEditorial(nombre);
            editorialRepositorio.save(editorial);
        } else {
            throw new NoSuchElementException("No se encontró la editorial con el ID: " + id);
        }
    }

     @Transactional
     public void desactivarEditorial(String id) {
         Optional<Editorial> respuesta = editorialRepositorio.findById(id);
         if (respuesta.isPresent()) { // Si encuentra el objeto por id
             Editorial editorial = respuesta.get();
             editorial.setEditorialActiva(false);
             editorialRepositorio.save(editorial);
         }else {
            throw new NoSuchElementException("No se encontró la editorial con el ID: " + id);
        }
     }


    @Transactional(readOnly = true)
    public Editorial getOne(String id) {
        return editorialRepositorio.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public EditorialDTO obtenerEditorialDTO(String id) {
        Optional<Editorial> optionalEditorial = editorialRepositorio.findById(id);
        Editorial editorial = null;
        try {
            // Verificamos si el valor está presente en el Optional
            if (optionalEditorial.isPresent()) {
                editorial = optionalEditorial.get();
            } else {
                throw new EntityNotFoundException("No se encontró la editorial con ID: " + id);
            }
        } catch (EntityNotFoundException e) {
            // Manejo de la excepción en caso de que no se encuentre la entidad
            throw e; // Lanzamos la excepción personalizada
        }
       
        // Mapear la entidad a DTO
        return new EditorialDTO(editorial.getId(), editorial.getNombreEditorial(), editorial.isEditorialActiva());
    }



}
