package com.loyalkicksproyect.service_pago.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loyalkicksproyect.service_pago.model.Pago;
import com.loyalkicksproyect.service_pago.repository.PagoRepository;

@Service
public class PagoService
{
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Pago registrarPago(Pago pago)
    {
        Object datosPedido = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/pedidos/" + pago.getPedidoId())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        pago.setDatosPedido(datosPedido);
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstado("APROBADO");
        return pagoRepository.save(pago);
    }

    public List<Pago> listarTodo()
    {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(Long id)
    {
        return pagoRepository.findById(id).map(this::enriquecerConPedido);
    }

    public List<Pago> listarPorPedido(Long pedidoId)
    {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    public Pago anularPago(Long id)
    {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pago.setEstado("ANULADO");
        return pagoRepository.save(pago);
    }

    private Pago enriquecerConPedido(Pago pago)
    {
        if(pago.getPedidoId() != null)
        {
            try
            {
                Object datosPedido = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8083/api/v1/pedidos/" + pago.getPedidoId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                pago.setDatosPedido(datosPedido);
            } catch (Exception e)
            {
                pago.setDatosPedido("Informacion de pedido no disponible actualmente");
            }
        }
        return pago;
    }
}
