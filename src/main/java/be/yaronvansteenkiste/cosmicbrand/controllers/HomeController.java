package be.yaronvansteenkiste.cosmicbrand.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String Home(Model model) {
        String category1 = "Make-up";
        String category2 = "Wellness";
        String category3 = "Utensils";
        model.addAttribute("category1", category1);
        model.addAttribute("category2", category2);
        model.addAttribute("category3", category3);
        return "home";
    }

    @GetMapping("/contact")
    public String Contact(Model model) {
        return "contact";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }
}
