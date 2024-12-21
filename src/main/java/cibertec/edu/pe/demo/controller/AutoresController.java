package cibertec.edu.pe.demo.controller;

import cibertec.edu.pe.demo.dto.AutorDto;
import cibertec.edu.pe.demo.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutoresController {

    @Autowired
    private MaintenanceService maintenanceService;

    // Mostrar la lista de autores
    @GetMapping("/showAuthorsPage")
    public String showAuthorsPage(Model model) {
        try {
            List<AutorDto> autores = maintenanceService.getAllAuthors();
            model.addAttribute("autores", autores);
            model.addAttribute("error", null);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos de los autores.");
        }
        return "autores"; // Vista para listar autores
    }

    // Mostrar detalles de un autor específico
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        try {
            AutorDto autorDto = maintenanceService.getAutormById(id);
            model.addAttribute("autorDto", autorDto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "No se pudo obtener la información del autor.");
        }
        return "detalles-autor"; // Vista para mostrar los detalles
    }

    // Mostrar formulario para agregar un autor
    @GetMapping("/crear")
    public String showCreateForm(Model model) {
        model.addAttribute("autorDto", new AutorDto(null, "", null)); // Modelo vacío para el formulario
        return "crear-autor"; // Vista del formulario de creación
    }

    // Procesar la creación de un nuevo autor
    @PostMapping("/crear")
    public String addAuthor(@ModelAttribute AutorDto autorDto, RedirectAttributes redirectAttributes) {
        try {
            boolean resultado = maintenanceService.addAuthor(autorDto);
            if (resultado) {
                redirectAttributes.addFlashAttribute("success", "Autor agregado exitosamente.");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo agregar el autor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al procesar la solicitud.");
        }
        return "redirect:/autores/showAuthorsPage";
    }

    // Mostrar formulario para editar un autor existente
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        try {
            AutorDto autorDto = maintenanceService.getAutormById(id);
            model.addAttribute("autorDto", autorDto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener los datos del autor para editar.");
        }
        return "editar-autor"; // Vista del formulario de edición
    }

    // Procesar la edición del autor
    @PostMapping("/editar-confirm")
    public String editConfirm(@ModelAttribute AutorDto autorDto, RedirectAttributes redirectAttributes) {
        try {
            boolean isUpdated = maintenanceService.updateAuthor(autorDto);
            if (isUpdated) {
                redirectAttributes.addFlashAttribute("success", "Autor actualizado exitosamente.");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo actualizar el autor.");
                return "redirect:/autores/editar/" + autorDto.id();
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al procesar la solicitud.");
        }
        return "redirect:/autores/showAuthorsPage";
    }


    // Eliminar un autor
    @PostMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            boolean resultado = maintenanceService.deleteAuthorById(id);
            if (resultado) {
                redirectAttributes.addFlashAttribute("success", "Autor eliminado exitosamente.");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el autor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al procesar la solicitud.");
        }
        return "redirect:/autores/showAuthorsPage";
    }
}
