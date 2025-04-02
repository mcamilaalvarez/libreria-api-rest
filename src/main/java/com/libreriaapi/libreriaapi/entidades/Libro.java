package com.libreriaapi.libreriaapi.entidades;


import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "libro")
public class Libro {
    
    @Id
    private Long id;

    private int ejemplares;

    private boolean libroActivo;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "idEditorial")
    private Editorial editorial;
}
