package be.yaronvansteenkiste.cosmicbrand.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Product {

    @Id
    private Integer id;
    private String productName;
    private String productDescription;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}