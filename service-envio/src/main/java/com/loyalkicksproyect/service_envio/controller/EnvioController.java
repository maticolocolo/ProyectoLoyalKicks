package com.loyalkicksproyect.service_envio.controller;

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

import com.loyalkicksproyect.service_envio.model.Envio;
import com.loyalkicksproyect.service_envio.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envios", description = "Despacho y seguimiento de pedidos")
public class EnvioController
{
    @Autowired
    private EnvioService envioService;

    @Operation(summary = "Crear un envio para un pedido")
    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio)
    {
        return ResponseEntity.ok(envioService.crearEnvio(envio));
    }

    @Operation(summary = "Listar todos los envios")
    @GetMapping
    public List<Envio> listarTodo()
    {
        return envioService.listarTodo();
    }

    @Operation(summary = "Obtener un envio con datos del pedido y la sucursal de origen")
    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscarPorId(@PathVariable Long id)
    {
        return envioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cambiar el estado de un envio")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Envio> cambiarEstado(@PathVariable Long id, @RequestParam String estado)
    {
        return ResponseEntity.ok(envioService.cambiarEstado(id, estado));
    }
}
