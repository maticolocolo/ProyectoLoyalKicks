package com.loyalkicksproyect.service_cliente.controller;

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
import com.loyalkicksproyect.service_cliente.model.Cliente;
import com.loyalkicksproyect.service_cliente.service.ClienteService;


@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController 
{
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodo()
    {
        return clienteService.listarTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id)
    {
        return clienteService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente)
    {
        return ResponseEntity.ok(clienteService.agregarCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> cambiarCliente(@PathVariable Long id, @RequestBody Cliente cliente)
    {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id)
    {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
    
}
