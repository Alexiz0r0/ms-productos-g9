package com.jaob.ms_productos.aggregates.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;
}
