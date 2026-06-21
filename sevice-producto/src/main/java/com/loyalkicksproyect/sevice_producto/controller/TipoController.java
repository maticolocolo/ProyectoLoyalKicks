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
import com.loyalkicksproyect.sevice_producto.model.Tipo;
import com.loyalkicksproyect.sevice_producto.service.TipoService;

@RestController
@RequestMapping("/api/v1/tipos")
public class TipoController 
{
    
    @Autowired
    private TipoService tipoService;


    @GetMapping
    public List<Tipo> listarTodoT()
    {
        return tipoService.listarTodoT();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> buscarPorIdT(@PathVariable Long id)
    {
        return tipoService.buscarPorIdT(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tipo> guardarTipo(@RequestBody Tipo tipo)
    {
        return ResponseEntity.ok(tipoService.guardarTipo(tipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable Long id)
    {
        tipoService.eliminarTipo(id);
        return ResponseEntity.noContent().build();
    }
}
