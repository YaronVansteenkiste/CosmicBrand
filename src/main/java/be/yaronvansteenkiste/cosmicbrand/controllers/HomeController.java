package be.yaronvansteenkiste.cosmicbrand.controllers;


import be.yaronvansteenkiste.cosmicbrand.model.Product;
import be.yaronvansteenkiste.cosmicbrand.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping({"/", "home"})
    public String home(Model model) {
        String category1 = "Make-up";
        String category2 = "Wellness";
        String category3 = "Utensils";
        model.addAttribute("category1", category1);
        model.addAttribute("category2", category2);
        model.addAttribute("category3", category3);


        Optional<Product> productFromDb = productRepository.findById(1);
        productFromDb.ifPresent(product -> model.addAttribute("product", product));


        return "home";
    }



    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }
}
