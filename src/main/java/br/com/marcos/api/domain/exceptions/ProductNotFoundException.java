package br.com.marcos.api.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Produto não encontrado no sistema.");
    }

}
