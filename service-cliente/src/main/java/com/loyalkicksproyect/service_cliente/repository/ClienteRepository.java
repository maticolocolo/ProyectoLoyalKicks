package com.loyalkicksproyect.service_cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loyalkicksproyect.service_cliente.model.Cliente;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
    Optional<Cliente> findById(Long id);
}
