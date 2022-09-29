package me.dio.carrinhoApi.repository;

import me.dio.carrinhoApi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
