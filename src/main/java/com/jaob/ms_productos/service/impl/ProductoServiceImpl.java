package com.jaob.ms_productos.service.impl;

import com.jaob.ms_productos.aggregates.constants.Constantes;
import com.jaob.ms_productos.aggregates.request.ProductoRequest;
import com.jaob.ms_productos.aggregates.response.ProductoDTO;
import com.jaob.ms_productos.aggregates.response.ResponseBase;
import com.jaob.ms_productos.entity.Producto;
import com.jaob.ms_productos.exceptions.ResourceNotFoundException;
import com.jaob.ms_productos.repository.ProductoRepository;
import com.jaob.ms_productos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    @Override
    public ResponseBase<ProductoDTO> crear(ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .precio(request.getPrecio())
                .categoria(request.getCategoria())
                .build();
        Producto producto1 = repository.save(producto);
        return new ResponseBase<>(
                Constantes.CODE_CREATED,
                false,
                Constantes.MESSAGE_CREATED,
                generarProductoDto(producto1));
    }

    @Override
    public ResponseBase<List<ProductoDTO>> listar() {
        List<Producto> list = repository.findAll();
        List<ProductoDTO> dtos = list.stream()
                .map(this::generarProductoDto)
                .toList();
        return new ResponseBase<>(
                Constantes.CODE_SUCCESSFUL,
                false,
                Constantes.MESSAGE_SUCCESSFUL,
                dtos);
    }

    @Override
    public ResponseBase<ProductoDTO> actualizar(Long id, ProductoRequest request) {
        Producto producto = obtenerProductoById(id);
        producto.setNombre(request.getNombre());
        producto.setCategoria(request.getCategoria());
        producto.setPrecio(request.getPrecio());
        Producto producto1 = repository.save(producto);

        return new ResponseBase<>(
                Constantes.CODE_SUCCESSFUL,
                false,
                Constantes.MESSAGE_UPDATED,
                generarProductoDto(producto1));
    }

    @Override
    public ResponseBase<ProductoDTO> eliminar(Long id) {
        Producto producto = obtenerProductoById(id);
        repository.delete(producto);
        return new ResponseBase<>(
                Constantes.CODE_SUCCESSFUL,
                false,
                Constantes.MESSAGE_DELETED,
                generarProductoDto(producto));
    }

    private ProductoDTO generarProductoDto(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .categoria(producto.getCategoria())
                .build();
    }

    private Producto obtenerProductoById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constantes.MESSAGE_NOT_FOUND));
    }
}
