package com.loyalkicksproyect.service_devolucion.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loyalkicksproyect.service_devolucion.model.Devolucion;
import com.loyalkicksproyect.service_devolucion.repository.DevolucionRepository;

@Service
public class DevolucionService
{
    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Devolucion solicitarDevolucion(Devolucion devolucion)
    {
        Object datosPedido = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/pedidos/" + devolucion.getPedidoId())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        devolucion.setDatosPedido(datosPedido);
        devolucion.setEstado("PENDIENTE");
        devolucion.setFechaSolicitud(LocalDate.now());
        return devolucionRepository.save(devolucion);
    }

    public List<Devolucion> listarTodo()
    {
        return devolucionRepository.findAll();
    }

    public Optional<Devolucion> buscarPorId(Long id)
    {
        return devolucionRepository.findById(id).map(this::enriquecer);
    }

    public Devolucion cambiarEstado(Long id, String nuevoEstado)
    {
        Devolucion devolucion = devolucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devolucion no encontrada"));
        devolucion.setEstado(nuevoEstado);
        return devolucionRepository.save(devolucion);
    }

    private Devolucion enriquecer(Devolucion devolucion)
    {
        if(devolucion.getPedidoId() != null)
        {
            try
            {
                Object datosPedido = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8083/api/v1/pedidos/" + devolucion.getPedidoId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                devolucion.setDatosPedido(datosPedido);
            } catch (Exception e)
            {
                devolucion.setDatosPedido("Informacion de pedido no disponible actualmente");
            }
        }

        if(devolucion.getProductoId() != null)
        {
            try
            {
                Object datosProducto = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/v1/productos/" + devolucion.getProductoId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                devolucion.setDatosProducto(datosProducto);
            } catch (Exception e)
            {
                devolucion.setDatosProducto("Informacion de producto no disponible actualmente");
            }
        }

        return devolucion;
    }
}
