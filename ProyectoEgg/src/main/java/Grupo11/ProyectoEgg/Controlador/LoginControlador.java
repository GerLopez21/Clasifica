
package Grupo11.ProyectoEgg.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class LoginControlador {
    
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");   
    }
    

}
