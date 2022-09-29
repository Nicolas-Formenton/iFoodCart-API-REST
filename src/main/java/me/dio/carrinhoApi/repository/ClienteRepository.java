package me.dio.carrinhoApi.repository;

import me.dio.carrinhoApi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
