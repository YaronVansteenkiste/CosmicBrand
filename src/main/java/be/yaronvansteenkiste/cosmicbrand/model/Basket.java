package be.yaronvansteenkiste.cosmicbrand.model;

import be.yaronvansteenkiste.cosmicbrand.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Basket {

    private List<Integer> selectedProducts;

    private List<String> selectedProductNames;
    private double productsTotalPrice;

    private ProductRepository productRepository;

    public Basket(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> getSelectedProductNames() {
        return selectedProductNames;
    }

    public void setSelectedProductNames(List<String> selectedProductNames) {
        this.selectedProductNames = selectedProductNames;
    }

    public List<Integer> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Integer> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public double getProductsTotalPrice() {
        return productsTotalPrice;
    }

    public void setProductsTotalPrice(double productsTotalPrice) {
        this.productsTotalPrice = productsTotalPrice;
    }

    public void addProduct(Product product, Integer productId) {
        if (selectedProducts == null) {
            selectedProducts = new ArrayList<>();
            selectedProductNames = new ArrayList<>();
            selectedProducts.add(productId);
            selectedProductNames.add(productRepository.findById(productId).orElse(new Product()).getProductName());
            productsTotalPrice += product.getPrice();
            System.out.println(selectedProducts);
            System.out.println(selectedProductNames);
            System.out.println(productsTotalPrice);
        } else {
            selectedProducts.add(productId);
            selectedProductNames.add(productRepository.findById(productId).orElse(new Product()).getProductName());
            productsTotalPrice += product.getPrice();
            System.out.println(selectedProducts);
            System.out.println(selectedProductNames);
            System.out.println(productsTotalPrice);
        }
    }

}
