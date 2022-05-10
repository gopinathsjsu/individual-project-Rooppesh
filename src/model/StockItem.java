package model;

import builder.StockItemBuilder;

public class StockItem {
    private String category;
    private String name;
    private Double price;
    private Integer quantity;

    public StockItem(StockItemBuilder item){
        this.category = item.category;
        this.name = item.name;
        this.price = item.price;
        this.quantity =item.quantity;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
}
