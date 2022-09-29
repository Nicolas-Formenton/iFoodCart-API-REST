package me.dio.carrinhoApi.repository;

import me.dio.carrinhoApi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
