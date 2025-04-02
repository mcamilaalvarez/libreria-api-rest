package com.libreriaapi.libreriaapi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaapi.libreriaapi.entidades.Editorial;
import com.libreriaapi.libreriaapi.repositorios.EditorialRepositorio;

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
}
