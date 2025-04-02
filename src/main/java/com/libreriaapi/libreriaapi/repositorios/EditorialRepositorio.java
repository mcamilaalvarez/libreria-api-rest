package com.libreriaapi.libreriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreriaapi.libreriaapi.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
}
