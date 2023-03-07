package be.yaronvansteenkiste.cosmicbrand.controllers;

import be.yaronvansteenkiste.cosmicbrand.model.Product;
import be.yaronvansteenkiste.cosmicbrand.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    @GetMapping("product/{id}")
    public String product (Model model, @PathVariable Integer id) {

        if (id==null) return "home";

        Optional<Product> productFromDb = productRepository.findById(id);

        productFromDb.ifPresent(product -> model.addAttribute("product", product));

        return "product";
    }

}
