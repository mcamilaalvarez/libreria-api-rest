package com.libreriaapi.libreriaapi.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaapi.libreriaapi.entidades.Autor;
import com.libreriaapi.libreriaapi.modelos.AutorDTO;
import com.libreriaapi.libreriaapi.repositorios.AutorRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre)  {
      
        Autor autor = new Autor();// Instancio un objeto del tipo Autor
        autor.setNombreAutor(nombre);// Seteo el atributo, con el valor recibido como parámetro
        autor.setAutorActivo(true);
        autorRepositorio.save(autor); // Persisto el dato en mi BBDD
    }

    

    // Metodo para modificar un autor
    @Transactional
    public void modificarAutor(String nombre, String id) {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) { // Si encuentra el objeto por id
            Autor autor = respuesta.get();
            autor.setNombreAutor(nombre);
            autorRepositorio.save(autor);
        } else {
            throw new NoSuchElementException("No se encontró el autor con ID :" + id);
        }
    }

     // Metodo para desactivar un autor
     @Transactional
     public void desactivarAutor(String id) {
         Optional<Autor> respuesta = autorRepositorio.findById(id);
         if (respuesta.isPresent()) { // Si encuentra el objeto por id
             Autor autor = respuesta.get();
             autor.setAutorActivo(false);// Seteo la informacion con el dato recibido
             autorRepositorio.save(autor);
         }
     }

      // Metodo para recuperar un autor
    @Transactional(readOnly = true)
    public Autor getOne(String id) {
        return autorRepositorio.getReferenceById(id);
    }

    // Metodo para recuperar una "lista de autores ACTIVOS y NO ACTIVOS "
    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional(readOnly = true)
    public AutorDTO obteneAutorDTO(String id){
        Optional<Autor> optionalAutor = autorRepositorio.findById(id);
        Autor autor = null;
        try {
            if(optionalAutor.isPresent()){
                autor = optionalAutor.get();
            } else{
                throw new EntityNotFoundException("No se pudo encontrar un autor con el id " + id);
            }
        } catch (EntityNotFoundException e) {
            throw e;
        }

        return new AutorDTO(autor.getId(),autor.getNombreAutor(), autor.isAutorActivo());
    }

}
