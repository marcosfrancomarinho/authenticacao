package br.com.marcos.api.domain.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Produto já cadastrado no sistema.");
    }

}
