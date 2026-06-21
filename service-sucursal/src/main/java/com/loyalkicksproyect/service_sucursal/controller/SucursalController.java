package com.loyalkicksproyect.service_sucursal.controller;

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

import com.loyalkicksproyect.service_sucursal.model.Sucursal;
import com.loyalkicksproyect.service_sucursal.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/sucursales")
@Tag(name = "Sucursales", description = "Gestión de las sucursales físicas de Loyal Kicks")
public class SucursalController
{
    @Autowired
    private SucursalService sucursalService;

    @Operation(summary = "Listar todas las sucursales")
    @GetMapping
    public List<Sucursal> listarTodo()
    {
        return sucursalService.listarTodo();
    }

    @Operation(summary = "Obtener una sucursal por id")
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscarPorId(@PathVariable Long id)
    {
        return sucursalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva sucursal")
    @PostMapping
    public ResponseEntity<Sucursal> agregarSucursal(@RequestBody Sucursal sucursal)
    {
        return ResponseEntity.ok(sucursalService.agregarSucursal(sucursal));
    }

    @Operation(summary = "Actualizar una sucursal existente")
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal)
    {
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id, sucursal));
    }

    @Operation(summary = "Eliminar una sucursal")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id)
    {
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
