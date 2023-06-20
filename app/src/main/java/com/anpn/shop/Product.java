package com.anpn.shop;

public class Product {
    private final int picture;//ссылка на изображение товара
    private final String name;//название товара
    private final String description;//описание товара;
    private final String price;//цена товара


    public Product(int picture, String name, String description, String price){
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.price = price;

    }
    public int getPicture(){
        return this.picture;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public String getPrice(){
        return this.price;
    }
}
