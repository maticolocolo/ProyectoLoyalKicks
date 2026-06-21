package com.loyalkicksproyect.service_envio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loyalkicksproyect.service_envio.model.Envio;
import com.loyalkicksproyect.service_envio.repository.EnvioRepository;

@Service
public class EnvioService
{
    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Envio crearEnvio(Envio envio)
    {
        envio.setEstado("PREPARANDO");
        envio.setFechaDespacho(LocalDate.now());
        envio.setFechaEntregaEstimada(LocalDate.now().plusDays(5));
        return envioRepository.save(envio);
    }

    public List<Envio> listarTodo()
    {
        return envioRepository.findAll();
    }

    public Optional<Envio> buscarPorId(Long id)
    {
        return envioRepository.findById(id).map(this::enriquecer);
    }

    public Envio cambiarEstado(Long id, String nuevoEstado)
    {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
        envio.setEstado(nuevoEstado);
        return envioRepository.save(envio);
    }

    private Envio enriquecer(Envio envio)
    {
        if(envio.getPedidoId() != null)
        {
            try
            {
                Object datosPedido = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8083/api/v1/pedidos/" + envio.getPedidoId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                envio.setDatosPedido(datosPedido);
            } catch (Exception e)
            {
                envio.setDatosPedido("Informacion de pedido no disponible actualmente");
            }
        }

        if(envio.getSucursalOrigenId() != null)
        {
            try
            {
                Object datosSucursal = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8086/api/v1/sucursales/" + envio.getSucursalOrigenId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                envio.setDatosSucursal(datosSucursal);
            } catch (Exception e)
            {
                envio.setDatosSucursal("Informacion de sucursal no disponible actualmente");
            }
        }

        return envio;
    }
}
