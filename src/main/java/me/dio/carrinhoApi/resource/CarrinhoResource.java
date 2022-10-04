package me.dio.carrinhoApi.resource;

import lombok.RequiredArgsConstructor;
import me.dio.carrinhoApi.model.Carrinho;
import me.dio.carrinhoApi.model.Item;
import me.dio.carrinhoApi.resource.dto.ItemDto;
import me.dio.carrinhoApi.service.CarrinhoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood/carrinhos") //Definição de URL para ver a sacola
@RequiredArgsConstructor
public class CarrinhoResource {
    private final CarrinhoService carrinhoService;
    @PostMapping
    public Item incluirItemCarrinho(@RequestBody ItemDto itemDto) {
        return carrinhoService.incluirItemCarrinho(itemDto);
    }

    @GetMapping("/{id}")
    public Carrinho verCarrinho(@PathVariable("id") Long id){
        return carrinhoService.verCarrinho(id);
    }

    @PatchMapping("/fecharCarrinho/{carrinhoId}")
    public Carrinho fecharCarrinho(@PathVariable("carrinhoId") Long carrinhoId,
                                   @RequestParam("formaPagamento") int formaPagamento){
        return carrinhoService.fecharCarrinho(carrinhoId, formaPagamento);
    }
}
