package be.yaronvansteenkiste.cosmicbrand.controllers;

import be.yaronvansteenkiste.cosmicbrand.model.Basket;
import be.yaronvansteenkiste.cosmicbrand.model.Product;
import be.yaronvansteenkiste.cosmicbrand.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BasketController {

    @Autowired
    private ProductRepository productRepository;

    private Basket basket;

    @Autowired
    public void setBasket() {
        basket = new Basket(productRepository);
    }

    @GetMapping("/basket/")
    public String Basket(Model model) {
        model.addAttribute("basket", basket);
        return "basket";
    }

    @PostMapping("/basket/add")
    @ResponseBody
    public void addToBasket(@RequestParam Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (basket == null) {
                basket = new Basket(productRepository);
            }
            basket.addProduct(product, productId);
            System.out.println("added product: " + productId);
        }
    }
}
