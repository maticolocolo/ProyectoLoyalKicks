package com.loyalkicksproyect.service_proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyect.service_proveedor.model.Proveedor;
import com.loyalkicksproyect.service_proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "Gestión de proveedores de calzado para Loyal Kicks")
public class ProveedorController
{
    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Listar todos los proveedores")
    @GetMapping
    public List<Proveedor> listarTodo()
    {
        return proveedorService.listarTodo();
    }

    @Operation(summary = "Obtener un proveedor por id")
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscarPorId(@PathVariable Long id)
    {
        return proveedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo proveedor")
    @PostMapping
    public ResponseEntity<Proveedor> agregarProveedor(@RequestBody Proveedor proveedor)
    {
        return ResponseEntity.ok(proveedorService.agregarProveedor(proveedor));
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor)
    {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(id, proveedor));
    }

    @Operation(summary = "Eliminar un proveedor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id)
    {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
