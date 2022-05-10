package builder;

import model.StockItem;

public class StockItemBuilder implements Builder{
    public String category;
    public String name;
    public Double price;
    public Integer quantity;

    public StockItemBuilder(String category, String name, Double price, Integer quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public StockItem build(){
        if(this.category == null || this.name == null)
            throw new RuntimeException("Values can not be null");

        return new StockItem(this);
    }
}