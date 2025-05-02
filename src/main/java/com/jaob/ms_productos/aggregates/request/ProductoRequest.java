package com.jaob.ms_productos.aggregates.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductoRequest {

    private String nombre;
    private Double precio;
    private String categoria;
}
