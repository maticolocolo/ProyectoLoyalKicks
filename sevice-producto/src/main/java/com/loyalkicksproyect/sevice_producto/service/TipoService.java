package com.loyalkicksproyect.sevice_producto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loyalkicksproyect.sevice_producto.model.Tipo;
import com.loyalkicksproyect.sevice_producto.repository.TipoRepository;

@Service
public class TipoService 
{
    @Autowired
    private TipoRepository tipoRepository;

    public List<Tipo> listarTodoT()
    {
        return tipoRepository.findAll();
    }

    public Optional<Tipo> buscarPorIdT(Long id)
    {
        return tipoRepository.findById(id);
    }

    public Tipo guardarTipo(Tipo tipo)
    {
        return tipoRepository.save(tipo);
    }

    public void eliminarTipo(Long id)
    {
        tipoRepository.deleteById(id);
    }
}
