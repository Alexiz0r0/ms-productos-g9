package com.jaob.ms_productos.service.impl;

import com.jaob.ms_productos.aggregates.constants.Constantes;
import com.jaob.ms_productos.aggregates.request.ProductoRequest;
import com.jaob.ms_productos.aggregates.response.ProductoDTO;
import com.jaob.ms_productos.aggregates.response.ResponseBase;
import com.jaob.ms_productos.entity.Producto;
import com.jaob.ms_productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductoServiceImplTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoServiceImpl service;

    private Producto producto;
    private ProductoRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        request = new ProductoRequest();

    }

    @Test
    void crear() {
        //ARRANGE
        when(repository.save(any(Producto.class))).thenReturn(producto);
        //ACT
        ResponseBase<ProductoDTO> response = service.crear(request);
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_CREATED, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_CREATED, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());

        verify(repository).save(any(Producto.class));
    }

    @Test
    void listar() {
        //ARRANGE
        List<Producto> productos = List.of(producto);
        when(repository.findAll()).thenReturn(productos);
        //ACT
        ResponseBase<List<ProductoDTO>> response = service.listar();
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_SUCCESSFUL, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_SUCCESSFUL, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());

        verify(repository).findAll();
    }

    @Test
    void actualizar() {
        //ARRANGE
        Long id = 1L;
        producto.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(producto));
        when(repository.save(any(Producto.class))).thenReturn(producto);
        //ACT
        ResponseBase<ProductoDTO> response = service.actualizar(id, request);
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_SUCCESSFUL, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_UPDATED, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());
        assertEquals(id, response.getData().getId());

        verify(repository).findById(id);
        verify(repository).save(any(Producto.class));
    }

    @Test
    void eliminar() {
        //ARRANGE
        Long id = 1L;
        producto.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(producto));
        doNothing().when(repository).delete(producto);
        //ACT
        ResponseBase<ProductoDTO> response = service.eliminar(id);
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_SUCCESSFUL, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_DELETED, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());

        verify(repository).findById(id);
        verify(repository).delete(producto);
    }
}