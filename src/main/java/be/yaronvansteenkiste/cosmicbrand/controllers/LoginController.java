package be.yaronvansteenkiste.cosmicbrand.controllers;

import be.yaronvansteenkiste.cosmicbrand.model.Login;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class LoginController {
    @PostMapping("/account")
    public String login(@ModelAttribute("login") Login login, Model model, HttpSession session) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcosmic", "root", "admin");
            String query = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Login logindb = new Login();
                logindb.setUsername(rs.getString("username"));
                session.setAttribute("login", logindb);
                return "redirect:/";
            }
            else {
                model.addAttribute("loggedin", false);
                model.addAttribute("error", "User not found.");
                return "login";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/authenticatedPage")
    public String authenticatedPage(Model model, HttpSession session) {
        Login login = (Login) session.getAttribute("login");
        if (login == null) {
            // User is not logged in, redirect to login page
            return "redirect:/login";
        } else {
            // User is logged in, set the "login" attribute in the model
            model.addAttribute("login", login);
            return "authenticatedPage";
        }
    }

    @GetMapping("/login/")
    public String Login(Model model, HttpSession session) {
        Login login = (Login) session.getAttribute("login");
        if (login == null) {
            model.addAttribute("loggedin", false);
        } else {
            model.addAttribute("loggedin", true);
            model.addAttribute("login", login);
        }
        return "login";
    }
}