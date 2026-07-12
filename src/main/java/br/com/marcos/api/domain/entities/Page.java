package br.com.marcos.api.domain.entities;

import br.com.marcos.api.domain.valuesobject.PageNumber;
import br.com.marcos.api.domain.valuesobject.Size;

public class Page {
    private Size size;
    private PageNumber number;

    public Page(Size size, PageNumber number) {
        this.size = size;
        this.number = number;
    }

    public int getSize() {
        return size.getValue();
    }

    public int getNumber() {
        return number.getValue();
    }

    public int getOffSet() {
        return size.getValue() * number.getValue();
    }
}
