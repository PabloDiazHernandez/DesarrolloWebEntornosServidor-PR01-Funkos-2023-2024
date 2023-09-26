package org.example.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class funkos {
    private UUID cod;
    private String nombre;
    private String modelo;
    private double precio;
    private LocalDateTime fecha_lanzamiento;
}
