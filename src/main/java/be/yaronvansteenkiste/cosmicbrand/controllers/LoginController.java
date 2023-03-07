package be.yaronvansteenkiste.cosmicbrand.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class LoginController {
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
                model.addAttribute("loggedin", true);
                return "redirect:/home";
            } else {
                model.addAttribute("loggedin", false);
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
