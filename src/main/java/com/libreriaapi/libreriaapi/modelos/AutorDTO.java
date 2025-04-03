package com.libreriaapi.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutorDTO {
    private String id;
    private String nombre;
    private boolean autorActivo;
}
