package Grupo11.ProyectoEgg.Controlador;

import Grupo11.ProyectoEgg.Entidad.Materiales;
import Grupo11.ProyectoEgg.Servicios.MaterialesServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/materiales")
public class MaterialesControlador {
   @Autowired
    private MaterialesServicio materialesServicio;
   
   @Autowired
    private ClasificadoresControlador cc;
           
           
    @GetMapping("/ver-todos")
    public ModelAndView mostrarTodos(){
        ModelAndView mav = new ModelAndView("lista-materiales");
        List<Materiales> materiales = materialesServicio.buscarTodos();
        mav.addObject("materiales", materiales);
        return mav;
    }
    
    
    @GetMapping("/crear")
    public ModelAndView agregarLibro(){
        ModelAndView mav= new ModelAndView("materiales-formulario");
        mav.addObject("material",new Materiales());
        
        mav.addObject("title","Agregar Materiales");
        
        mav.addObject("action","guardar");
        return mav;
    }   
    
    
//    @PostMapping("/guardar")//Metodo que se usa para los formularios
//    public RedirectView guardar(@RequestParam Long carton, @RequestParam Long plastico, @RequestParam Long vidrio, @RequestParam Long latas, @RequestParam Long papel){
//        materialesServicio.crear(carton, plastico, vidrio, latas, papel);
//        return new RedirectView(cc.perfilClasificador(vidrio));
//    }
}
