package com.jaob.ms_productos.service;

import com.jaob.ms_productos.aggregates.request.ProductoRequest;
import com.jaob.ms_productos.aggregates.response.ProductoDTO;
import com.jaob.ms_productos.aggregates.response.ResponseBase;

import java.util.List;

public interface ProductoService {
    ResponseBase<ProductoDTO> crear(ProductoRequest request);

    ResponseBase<List<ProductoDTO>> listar();

    ResponseBase<ProductoDTO> actualizar(Long id, ProductoRequest request);

    ResponseBase<ProductoDTO> eliminar(Long id);
}
