package com.loyalkicksproyect.service_pago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loyalkicksproyect.service_pago.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long>
{
    List<Pago> findByPedidoId(Long pedidoId);
}
