package me.dio.carrinhoApi.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.carrinhoApi.enumeration.FormaPagamento;
import me.dio.carrinhoApi.model.Carrinho;
import me.dio.carrinhoApi.model.Item;
import me.dio.carrinhoApi.model.Restaurante;
import me.dio.carrinhoApi.repository.CarrinhoRepository;
import me.dio.carrinhoApi.repository.ProdutoRepository;
import me.dio.carrinhoApi.repository.ItemRepository;
import me.dio.carrinhoApi.resource.dto.ItemDto;
import me.dio.carrinhoApi.service.CarrinhoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoServiceImpl implements CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemCarrinho(ItemDto itemDto) {
        Carrinho carrinho = verCarrinho(itemDto.getCarrinhoId());

        if (carrinho.isFechada()) {
            throw new RuntimeException("Este carrinho está fechado!");
        }

        Item itemParSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .carrinho(carrinho)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Produto não encontrado");
                        }
                ))
                .build();

        List<Item> itensDoCarrinho = carrinho.getItens();
        if(itensDoCarrinho.isEmpty()) {
            itensDoCarrinho.add(itemParSerInserido);
        }
        else{
            Restaurante restauranteAtual = itensDoCarrinho.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParSerInserido.getProduto().getRestaurante();

            if(restauranteAtual.equals(restauranteDoItemParaAdicionar)){
                itensDoCarrinho.add(itemParSerInserido);
            }
            else{
                throw new RuntimeException("Não é possível adicionar produtos de diferentes restaurantes. Feche o " +
                        "carrinho ou esvazie.");
            }
        }

        //Lógica de cálculo valorTotal
        List<Double> valorDosItens = new ArrayList<>();

        for(Item itemDoCarrinho: itensDoCarrinho){
            double valorTotalItem = itemDoCarrinho.getProduto().getValorUnitario() * itemDoCarrinho.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

//        Double valorTotalCarrinho = 0.0;
//        for(Double valorDeCadaItem:valorDosItens){
//            valorTotalCarrinho += valorDeCadaItem;
//        }

        double valorTotalCarrinho = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        carrinho.setValorTotal(valorTotalCarrinho);

        carrinhoRepository.save(carrinho);
        return itemRepository.save(itemParSerInserido);
    }

    @Override
    public Carrinho verCarrinho(Long id) {
        return carrinhoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Esse carrinho não existe!");
                }
        );
    }

    @Override
    public Carrinho fecharCarrinho(Long id, int numeroformaPagamento) {
        Carrinho carrinho = verCarrinho(id);

        if(carrinho.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens no carrinho!");
        }

//        if (numeroformaPagamento == 0) {
//            carrinho.setFormaPagamento(FormaPagamento.DINHEIRO);
//        } else {
//            carrinho.setFormaPagamento(FormaPagamento.MAQUINETA);
//        }

        FormaPagamento formaPagamento =
                numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        carrinho.setFormaPagamento(formaPagamento);
        carrinho.setFechada(true);
        return carrinhoRepository.save(carrinho);

    }
}
