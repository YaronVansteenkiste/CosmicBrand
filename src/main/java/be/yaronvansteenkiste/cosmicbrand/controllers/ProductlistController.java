package be.yaronvansteenkiste.cosmicbrand.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductlistController {
    @GetMapping("productlist")
    public String Productlist (Model model) {

        return "productlist";
    }
}
