package com.loyalkicksproyect.service_pago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyect.service_pago.model.Pago;
import com.loyalkicksproyect.service_pago.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Registro y consulta de pagos asociados a pedidos")
public class PagoController
{
    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Registrar el pago de un pedido")
    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago)
    {
        return ResponseEntity.ok(pagoService.registrarPago(pago));
    }

    @Operation(summary = "Listar todos los pagos")
    @GetMapping
    public List<Pago> listarTodo()
    {
        return pagoService.listarTodo();
    }

    @Operation(summary = "Obtener un pago por id, incluye datos del pedido")
    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id)
    {
        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar los pagos de un pedido")
    @GetMapping("/pedido/{pedidoId}")
    public List<Pago> listarPorPedido(@PathVariable Long pedidoId)
    {
        return pagoService.listarPorPedido(pedidoId);
    }

    @Operation(summary = "Anular un pago")
    @PutMapping("/{id}/anular")
    public ResponseEntity<Pago> anularPago(@PathVariable Long id)
    {
        return ResponseEntity.ok(pagoService.anularPago(id));
    }
}
