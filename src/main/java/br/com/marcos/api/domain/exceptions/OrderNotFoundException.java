package br.com.marcos.api.domain.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Nenhum pedido encontrado com o id: " + id);
    }
}