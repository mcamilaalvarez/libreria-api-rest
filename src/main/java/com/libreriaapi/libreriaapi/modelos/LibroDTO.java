package com.libreriaapi.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibroDTO {
    private Long isbn;
    private String titulo;
    private int ejemplares;
}
