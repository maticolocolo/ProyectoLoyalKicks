package com.loyalkicksproyecto.service_pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyecto.service_pedido.model.Pedido;
import com.loyalkicksproyecto.service_pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoController 
{
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarTodo()
    {
        return pedidoService.listarTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id)
    {
        return pedidoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido)
    {
        return ResponseEntity.ok(pedidoService.crearPedido(pedido));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long id, @RequestParam String estado)
    {
        return ResponseEntity.ok(pedidoService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id)
    {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
