package com.loyalkicksproyect.service_sucursal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loyalkicksproyect.service_sucursal.model.Sucursal;
import com.loyalkicksproyect.service_sucursal.repository.SucursalRepository;

@Service
public class SucursalService
{
    @Autowired
    private SucursalRepository sucursalRepository;

    public Sucursal agregarSucursal(Sucursal sucursal)
    {
        if(sucursal.getActiva() == null)
        {
            sucursal.setActiva(true);
        }
        return sucursalRepository.save(sucursal);
    }

    public List<Sucursal> listarTodo()
    {
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> buscarPorId(Long id)
    {
        return sucursalRepository.findById(id);
    }

    public Sucursal actualizarSucursal(Long id, Sucursal datos)
    {
        Sucursal s = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        s.setNombre(datos.getNombre());
        s.setDireccion(datos.getDireccion());
        s.setComuna(datos.getComuna());
        s.setTelefono(datos.getTelefono());
        s.setActiva(datos.getActiva());
        return sucursalRepository.save(s);
    }

    public void eliminarSucursal(Long id)
    {
        sucursalRepository.deleteById(id);
    }
}
