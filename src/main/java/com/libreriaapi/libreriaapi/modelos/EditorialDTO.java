package com.libreriaapi.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditorialDTO {
    private String id;
    private String nombreEditorial;
    private boolean editorialActiva;
}
