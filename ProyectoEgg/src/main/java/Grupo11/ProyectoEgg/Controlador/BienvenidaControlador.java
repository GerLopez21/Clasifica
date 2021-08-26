package Grupo11.ProyectoEgg.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class BienvenidaControlador {
    @GetMapping
    public ModelAndView inicio(){
        return new ModelAndView("Bienvenida");
    }
    
    @GetMapping("/error-403")
    public ModelAndView error(){
        return new ModelAndView("error-403");
    }
   
}