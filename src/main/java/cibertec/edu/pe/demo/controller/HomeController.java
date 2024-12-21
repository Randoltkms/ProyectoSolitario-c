package cibertec.edu.pe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/randol")
public class HomeController {

    // Mapeo para la página de inicio
    @GetMapping("/Home")
    public String showHomePage(Model model) {
        model.addAttribute("body");
        return "index";  // Asegúrate de que el nombre de la vista coincida con el nombre del archivo HTML
    }
}
