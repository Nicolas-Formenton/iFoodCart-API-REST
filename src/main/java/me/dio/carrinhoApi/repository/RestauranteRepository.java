package me.dio.carrinhoApi.repository;

import me.dio.carrinhoApi.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
