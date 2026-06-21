package com.loyalkicksproyect.service_inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.loyalkicksproyect.service_inventario.model.Inventario;
import com.loyalkicksproyect.service_inventario.model.Talla;
import com.loyalkicksproyect.service_inventario.repository.InventarioRepository;
import com.loyalkicksproyect.service_inventario.repository.TallaRepository;

@Service
public class InventarioService 
{
    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Inventario guardarInventario(Inventario inventario)
    {
        if(inventario.getProductoId() != null)
        {
            Object datosProducto = webClientBuilder.build()
            .get()
            .uri("http://localhost:8081/api/v1/productos/" + inventario.getProductoId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();

            inventario.setDatosProducto(datosProducto);
        }

        if(inventario.getTalla() != null && inventario.getTalla().getId() != null)
        {
            Talla talla = tallaRepository.findById(inventario.getTalla().getId())
            .orElseThrow(() -> new RuntimeException("Talla no encontrada"));
            inventario.setTalla(talla);
        }

        return inventarioRepository.save(inventario);
    }

    public List<Inventario> listarTodo()
    {
        return inventarioRepository.findAll();
    }

    public Inventario obtenerInventarioCompleto(Long id)
    {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if(inventario != null)
        {
            return enriquecerConProducto(inventario);
        }
        return null;
    }

    public List<Inventario> listarPorProducto(Long productoId)
    {
        return inventarioRepository.findByProductoId(productoId);
    }

    //Metodos para Talla

    public Talla guardarTalla(Talla talla)
    {
        return tallaRepository.save(talla);
    }

    public List<Talla> listarTallas()
    {
        return tallaRepository.findAll();
    }

    public Optional<Talla> buscarPorTId(Long id)
    {
        return tallaRepository.findById(id);
    }

    public void deleteTalla(Long id)
    {
        tallaRepository.deleteById(id);
    }

    private Inventario enriquecerConProducto(Inventario inventario)
    {
        if(inventario.getProductoId() != null)
        {
            try
            {
                Object datosProducto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/productos/" + inventario.getProductoId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                inventario.setDatosProducto(datosProducto);
            } catch(Exception e)
            {
                inventario.setDatosProducto("informacion de producto no disponible");
            }
        }
        return inventario;
    }
}
