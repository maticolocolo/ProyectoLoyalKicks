package com.loyalkicksproyect.sevice_producto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.loyalkicksproyect.sevice_producto.model.Producto;
import com.loyalkicksproyect.sevice_producto.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController 
{
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarTodoP()
    {
        return productoService.listarTodoP();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorIdP(@PathVariable Long id)
    {
        return productoService.buscarPorIdP(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto)
    {
        return ResponseEntity.ok(productoService.guardaProducto(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id)
    {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
