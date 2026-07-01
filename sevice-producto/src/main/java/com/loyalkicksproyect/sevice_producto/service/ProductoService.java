package com.loyalkicksproyect.sevice_producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loyalkicksproyect.sevice_producto.model.Producto;
import com.loyalkicksproyect.sevice_producto.repository.ProductoRespository;

@Service
public class ProductoService 
{
    @Autowired
    private ProductoRespository productoRespository;

    public List<Producto> listarTodoP()
    {
        return productoRespository.findAll();
    }

    public Optional<Producto> buscarPorIdP(Long id)
    {
        return productoRespository.findById(id);
    }

    public Producto guardaProducto(Producto producto)
    {
        return productoRespository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto datos)
    {
        Producto p = productoRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        p.setNombre_producto(datos.getNombre_producto());
        p.setModelo(datos.getModelo());
        p.setMarca(datos.getMarca());
        p.setTipo(datos.getTipo());
        return productoRespository.save(p);
    }

    public void eliminarProducto(Long id)
    {
        productoRespository.deleteById(id);
    }
}
