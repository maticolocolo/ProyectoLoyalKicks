package com.loyalkicksproyecto.service_pedido.service;

import com.loyalkicksproyecto.service_pedido.repository.PedidoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loyalkicksproyecto.service_pedido.model.DetallePedido;
import com.loyalkicksproyecto.service_pedido.model.Pedido;
import com.loyalkicksproyecto.service_pedido.repository.DetallePedidoRepository;

@Service
public class PedidoService 
{
   private final PedidoRepository pedidoRepository;

   @Autowired
   private DetallePedidoRepository detallePedidoRepository;

   @Autowired
   private WebClient.Builder webClientBuilder;


   PedidoService(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
   }


   public Pedido crearPedido(Pedido pedido)
   {
    pedido.setFecha(LocalDateTime.now());
    pedido.setEstado("Pendiente");
    return pedidoRepository.save(pedido);
   }

   public List<Pedido> listarTodo()
   {
    return pedidoRepository.findAll();
   }

   public Optional<Pedido> buscarPorId(Long Id)
   {
    return pedidoRepository.findById(Id);
   }

   public Pedido cambiarEstado(Long Id, String nuevoEstado)
   {
    Pedido pedido = pedidoRepository.findById(Id)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    pedido.setEstado(nuevoEstado);
    return pedidoRepository.save(pedido);
   }

   public void eliminarPedido(Long id)
   {
    pedidoRepository.deleteById(id);
   }

   public DetallePedido agregarDetalles(DetallePedido detalle)
   {
    if(detalle.getProductoId() != null)
    {
        try 
        {
            Object datosProducto = webClientBuilder.build()
            .get()
            .uri("http://localhost:8081/api/v1/productos/" + detalle.getProductoId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();
            
            detalle.setDatosProducto(datosProducto);
        } catch (Exception e) 
        {
            detalle.setDatosProducto("Producto no disponible");
        }
    }
    return detallePedidoRepository.save(detalle); 
   }

   public List<DetallePedido> obtenerDetallePedido(Long pedidoId)
   {
    return detallePedidoRepository.findByPedidoId(pedidoId);
   }

}
