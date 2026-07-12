package br.com.marcos.api.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Produto id " + id + " não encontrado no sistema.");
    }

}
