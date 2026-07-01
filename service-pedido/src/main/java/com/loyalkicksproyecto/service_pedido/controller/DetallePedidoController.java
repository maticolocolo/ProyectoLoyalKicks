package com.loyalkicksproyecto.service_pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyecto.service_pedido.model.DetallePedido;
import com.loyalkicksproyecto.service_pedido.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class DetallePedidoController 
{
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/detalle")
    public ResponseEntity<DetallePedido> agregarDetalles(@RequestBody DetallePedido detallePedido)
    {
        return ResponseEntity.ok(pedidoService.agregarDetalles(detallePedido));
    }

    @GetMapping("/{pedidoId}/detalle")
    public ResponseEntity<List<DetallePedido>> listarDetalles(@PathVariable Long pedidoId)
    {
        return ResponseEntity.ok(pedidoService.obtenerDetallePedido(pedidoId));
    }
}
