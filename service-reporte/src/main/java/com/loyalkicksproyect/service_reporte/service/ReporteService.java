package com.loyalkicksproyect.service_reporte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loyalkicksproyect.service_reporte.model.PedidoResumen;
import com.loyalkicksproyect.service_reporte.model.ReporteProducto;
import com.loyalkicksproyect.service_reporte.model.ReporteVentas;

@Service
public class ReporteService
{
    @Autowired
    private WebClient.Builder webClientBuilder;

    public ReporteVentas generarReporteVentas()
    {
        List<PedidoResumen> pedidos = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/pedidos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PedidoResumen>>() {})
                .block();

        if(pedidos == null)
        {
            pedidos = List.of();
        }

        Double totalVendido = pedidos.stream()
                .mapToDouble(p -> p.getTotal() != null ? p.getTotal() : 0.0)
                .sum();

        Double ticketPromedio = pedidos.isEmpty() ? 0.0 : totalVendido / pedidos.size();

        return new ReporteVentas(pedidos.size(), totalVendido, ticketPromedio, pedidos);
    }

    public ReporteProducto generarReporteProducto(Long productoId)
    {
        Object datosProducto;
        try
        {
            datosProducto = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/productos/" + productoId)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (Exception e)
        {
            datosProducto = "Producto no disponible";
        }

        Object datosInventario;
        try
        {
            datosInventario = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/v1/inventario/producto/" + productoId)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (Exception e)
        {
            datosInventario = "Inventario no disponible";
        }

        return new ReporteProducto(productoId, datosProducto, datosInventario);
    }
}
