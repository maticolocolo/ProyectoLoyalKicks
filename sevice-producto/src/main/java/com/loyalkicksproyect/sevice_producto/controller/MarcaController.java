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
import com.loyalkicksproyect.sevice_producto.model.Marca;
import com.loyalkicksproyect.sevice_producto.service.MarcaService;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController 
{
    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> listarTodoM()
    {
        return marcaService.listarTodoM();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorIdM(@PathVariable Long id)
    {
        return marcaService.buscarPorIdM(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marca> guardarMarca(@RequestBody Marca marca)
    {
        return ResponseEntity.ok(marcaService.guardarMarca(marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id)
    {
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }   
}
