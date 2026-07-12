package br.com.marcos.api.app.usecase;

import java.util.List;

import br.com.marcos.api.app.dtos.ProductFinderInput;
import br.com.marcos.api.app.dtos.ProductFinderOutput;
import br.com.marcos.api.domain.entities.Page;
import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.repository.ProductFinder;
import br.com.marcos.api.domain.valuesobject.PageNumber;
import br.com.marcos.api.domain.valuesobject.Size;

public class ProductFinderUseCase {
    private ProductFinder productFinder;

    public ProductFinderUseCase(ProductFinder productFinder) {
        this.productFinder = productFinder;
    }

    public List<ProductFinderOutput> findAll(ProductFinderInput payload) {
        PageNumber pageNumber = new PageNumber(payload.pageNumber());
        Size size = new Size(payload.size());
        Page page = new Page(size, pageNumber);

        List<Product> products = this.productFinder.findAll(page);

        return products.stream().map(product -> {
            return new ProductFinderOutput(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription());
        }).toList();
    }
}
