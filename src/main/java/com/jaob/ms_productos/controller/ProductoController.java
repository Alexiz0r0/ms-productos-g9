package com.jaob.ms_productos.controller;

import com.jaob.ms_productos.aggregates.request.ProductoRequest;
import com.jaob.ms_productos.aggregates.response.ProductoDTO;
import com.jaob.ms_productos.aggregates.response.ResponseBase;
import com.jaob.ms_productos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<ProductoDTO>> crear(@RequestBody ProductoRequest request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<ProductoDTO>>> listar() {
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseBase<ProductoDTO>> actualizar(@PathVariable Long id, @RequestBody ProductoRequest request) {
        return new ResponseEntity<>(service.actualizar(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseBase<ProductoDTO>> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }
}
