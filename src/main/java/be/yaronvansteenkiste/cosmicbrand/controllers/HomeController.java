package be.yaronvansteenkiste.cosmicbrand.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;


@Controller
public class HomeController {

    @GetMapping({"/", "home"})
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

    @PostMapping("/account")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:dbcosmic", "admin", "");
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "redirect:/home";
            } else {
                model.addAttribute("error", "User not found.");
                return "login";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }
}
