package com.loyalkicksproyecto.service_pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loyalkicksproyecto.service_pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>
{

}
