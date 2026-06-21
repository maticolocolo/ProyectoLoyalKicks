package com.loyalkicksproyect.service_proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyect.service_proveedor.model.ProductoProveedor;
import com.loyalkicksproyect.service_proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/proveedores/productos")
@Tag(name = "Producto-Proveedor", description = "Relación entre productos y sus proveedores")
public class ProductoProveedorController
{
    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Asociar un producto a un proveedor")
    @PostMapping
    public ResponseEntity<ProductoProveedor> asociarProducto(@RequestBody ProductoProveedor relacion)
    {
        return ResponseEntity.ok(proveedorService.asociarProducto(relacion));
    }

    @Operation(summary = "Listar los proveedores asociados a un producto")
    @GetMapping("/{productoId}")
    public List<ProductoProveedor> listarPorProducto(@PathVariable Long productoId)
    {
        return proveedorService.listarPorProducto(productoId);
    }
}
