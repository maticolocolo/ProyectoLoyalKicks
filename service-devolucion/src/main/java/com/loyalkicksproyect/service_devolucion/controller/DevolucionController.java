package com.loyalkicksproyect.service_devolucion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyect.service_devolucion.model.Devolucion;
import com.loyalkicksproyect.service_devolucion.service.DevolucionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/devoluciones")
@Tag(name = "Devoluciones", description = "Gestión de solicitudes de devolución de productos")
public class DevolucionController
{
    @Autowired
    private DevolucionService devolucionService;

    @Operation(summary = "Solicitar una devolución para un pedido")
    @PostMapping
    public ResponseEntity<Devolucion> solicitarDevolucion(@RequestBody Devolucion devolucion)
    {
        return ResponseEntity.ok(devolucionService.solicitarDevolucion(devolucion));
    }

    @Operation(summary = "Listar todas las devoluciones")
    @GetMapping
    public List<Devolucion> listarTodo()
    {
        return devolucionService.listarTodo();
    }

    @Operation(summary = "Obtener una devolución con datos del pedido y del producto")
    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarPorId(@PathVariable Long id)
    {
        return devolucionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cambiar el estado de una devolución")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Devolucion> cambiarEstado(@PathVariable Long id, @RequestParam String estado)
    {
        return ResponseEntity.ok(devolucionService.cambiarEstado(id, estado));
    }
}
