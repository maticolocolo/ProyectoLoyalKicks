package com.loyakicksproyecto.service_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loyakicksproyecto.service_auth.model.Rol;
import java.util.Optional;


public interface RolRepository extends JpaRepository<Rol, Long> 
{
    Optional<Rol> findByNombreRol(String nombreRol);
}
