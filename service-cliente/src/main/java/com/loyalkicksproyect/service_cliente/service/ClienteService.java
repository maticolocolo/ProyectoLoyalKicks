package com.loyalkicksproyect.service_cliente.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loyalkicksproyect.service_cliente.model.Cliente;
import com.loyalkicksproyect.service_cliente.repository.ClienteRepository;

@Service
public class ClienteService 
{
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente agregarCliente(Cliente cliente)
    {
        cliente.setFechaRegistro(LocalDate.now());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodo()
    {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id)
    {
        return clienteRepository.findById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente datos)
    {
        Cliente c = clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        c.setNombre(datos.getNombre());
        c.setApellido(datos.getApellido());
        c.setEmail(datos.getEmail());
        c.setTelefono(datos.getTelefono());
        c.setDirecion(datos.getDirecion());
        return clienteRepository.save(c);
    }

    public void eliminarCliente(Long id)
    {
        clienteRepository.deleteById(id);
    }
}
