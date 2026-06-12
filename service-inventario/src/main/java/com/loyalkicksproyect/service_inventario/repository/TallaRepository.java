package com.loyalkicksproyect.service_inventario.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.loyalkicksproyect.service_inventario.model.Talla;

public interface TallaRepository extends JpaRepository<Talla, Long> 
{
    Optional<Talla> findById(Long id);
}
