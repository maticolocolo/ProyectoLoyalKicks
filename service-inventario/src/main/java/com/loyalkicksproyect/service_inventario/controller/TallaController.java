package com.loyalkicksproyect.service_inventario.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyect.service_inventario.model.Talla;
import com.loyalkicksproyect.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/v1/inventario/tallas")
public class TallaController 
{
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public Talla aniadir(@RequestBody Talla talla)
    {
        return inventarioService.guardarTalla(talla);
    }

    @GetMapping("/{id}")
    public Optional<Talla> buscarPorId(@PathVariable Long id)
    {
        return inventarioService.buscarPorTId(id);
    }

    @GetMapping
    public List<Talla> listarTallas()
    {
        return inventarioService.listarTallas();
    }
}
