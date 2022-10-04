package me.dio.carrinhoApi.service;

import me.dio.carrinhoApi.model.Carrinho;
import me.dio.carrinhoApi.model.Item;
import me.dio.carrinhoApi.resource.dto.ItemDto;

public interface CarrinhoService {
    Item incluirItemCarrinho(ItemDto itemDto);
    Carrinho verCarrinho(Long id);
    Carrinho fecharCarrinho(Long id, int formaPagamento);
}
