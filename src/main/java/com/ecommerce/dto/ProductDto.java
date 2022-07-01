package com.ecommerce.dto;

import javax.validation.constraints.NotNull;



public class ProductDto {
    private Integer id;

    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer category_id;

    public ProductDto(){
        
    }

    
    public ProductDto(Integer id, @NotNull String name, @NotNull String imageURL, @NotNull double price,
            @NotNull String description, @NotNull Integer category_id) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.category_id = category_id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getCategory_id() {
        return category_id;
    }
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }


    

}
