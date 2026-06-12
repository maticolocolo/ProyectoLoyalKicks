package com.loyalkicksproyect.sevice_producto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loyalkicksproyect.sevice_producto.model.Marca;
import com.loyalkicksproyect.sevice_producto.repository.MarcaRespository;

@Service
public class MarcaService 
{
    @Autowired
    private MarcaRespository marcaRespository;

    public List<Marca> listarTodoM()
    {
        return marcaRespository.findAll();
    }

    public Optional<Marca> buscarPorIdM(Long id)
    {
        return marcaRespository.findById(id);
    }

    public Marca guardarMarca(Marca marca)
    {
        return marcaRespository.save(marca);
    }

    public void eliminarMarca(Long id)
    {
        marcaRespository.deleteById(id);
    }
}