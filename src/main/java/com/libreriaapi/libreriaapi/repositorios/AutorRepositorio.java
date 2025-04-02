package com.libreriaapi.libreriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreriaapi.libreriaapi.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor ,String> {
    
}
