package be.yaronvansteenkiste.cosmicbrand.controllers;

import be.yaronvansteenkiste.cosmicbrand.model.Datahandler;
import be.yaronvansteenkiste.cosmicbrand.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private DataRepository dataRepository;

    @GetMapping({"/", "home"})
    public String home(Model model) {
        String category1 = "Make-up";
        String category2 = "Wellness";
        String category3 = "Utensils";
        model.addAttribute("category1", category1);
        model.addAttribute("category2", category2);
        model.addAttribute("category3", category3);

        return "home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Datahandler> image = dataRepository.findById(id);
        if (image.isPresent()) {
            byte[] imageData = image.get().getData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/load-image")
    public String loadImage() throws IOException {
        URL url = new URL("https://post.healthline.com/wp-content/uploads/2020/04/makeup_composition_overhead-1200x628-facebook-1200x628.jpg");
        byte[] imageBytes = url.openStream().readAllBytes();
        Datahandler datahandler = new Datahandler();
        datahandler.setData(imageBytes);
        dataRepository.save(datahandler);
        return "redirect:/home";
    }
}
