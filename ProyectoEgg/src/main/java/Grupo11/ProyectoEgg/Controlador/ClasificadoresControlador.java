package Grupo11.ProyectoEgg.Controlador;

import Grupo11.ProyectoEgg.Entidad.Clasificadores;
import Grupo11.ProyectoEgg.Entidad.Materiales;
import Grupo11.ProyectoEgg.Errores.WebException;
import Grupo11.ProyectoEgg.Servicios.ClasificadoresServicios;
import Grupo11.ProyectoEgg.Servicios.EmailServicio;
import Grupo11.ProyectoEgg.Servicios.MaterialesServicio;
import Grupo11.ProyectoEgg.Servicios.RolServicio;
import Grupo11.ProyectoEgg.Servicios.RutaServicio;
import static java.lang.ProcessBuilder.Redirect.to;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clasificadores")
public class ClasificadoresControlador {

    @Autowired
    private ClasificadoresServicios clasificadoresServicios;

    @Autowired
    private RutaServicio rutaServicio;

    @Autowired
    private MaterialesServicio materialesServicio;

    @Autowired
    private RolServicio rolesServicio;
    @Autowired
    private EmailServicio emailServicio;

    @GetMapping("/perfil-clasificador/{dni}")
    public ModelAndView perfilClasificador(@PathVariable Long dni, HttpSession session) {

        if (!session.getAttribute("dni").equals(dni)) {
            return new ModelAndView(new RedirectView("/error-403"));
        }

        ModelAndView mav = new ModelAndView("perfil-clasificador");
        mav.addObject("material", new Materiales());
        Clasificadores clasificador = clasificadoresServicios.buscarPorId(dni);
        mav.addObject("rutas", rutaServicio.buscarTodos());
        mav.addObject("perfil", clasificador);
        mav.addObject("title", "Mi perfil");
        mav.addObject("action", "sumarRuta");
        return mav;
    }

    @PostMapping("/sumarRuta/{dni}")
    public RedirectView sumarRuta(@PathVariable Long dni, @RequestParam String idRuta, Materiales material) {
        clasificadoresServicios.AgregarRuta(dni, idRuta, material);
        return new RedirectView("/");
    }

    @GetMapping("/ver-todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView buscarTodos() {
        ModelAndView mav = new ModelAndView("clasificadores-lista");
        List<Clasificadores> clasificadores = clasificadoresServicios.buscarTodos();
        mav.addObject("clasificadores", clasificadores);
        mav.addObject("title", "Ver Todos");
        return mav;
    }
    
  /*  @GetMapping("/crear")
    // si tiene clasificador ADMIN lo va a dejar hacerlo
    //si quiere acceder con muchos roles hacer:
    //@PreAuthorize("hasAnyRole('ADMIN', 'CLASIFICADOR')")
    //@PreAuthorize("hasRole('ADMIN')")
    public ModelAndView crearClasificador(Model model) {
      ModelAndView mav = new ModelAndView("clasificadores-formulario");
        mav.addObject("clasificadores", new Clasificadores());
        mav.addObject("roles", rolesServicio.buscarTodos());
        mav.addObject("title", "Crear Clasificadores");
        mav.addObject("action", "guardar");
        return mav;
    }*/
    @GetMapping("/crear")
    public String crearClasificador(Model model){
        
        model.addAttribute("roles", rolesServicio.buscarTodos());
        model.addAttribute("action", "guardar");
        return "clasificadores-formulario.html";
    }
    
    @GetMapping("/editar/{dni}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLASIFICADOR')")
    public ModelAndView editarClasificador(@PathVariable Long dni, HttpSession session) {
        
        if (!session.getAttribute("dni").equals(dni)) {
            return new ModelAndView(new RedirectView("/error-403"));
        }

        ModelAndView mav = new ModelAndView("clasificadores-formulario");
        mav.addObject("clasificadores", clasificadoresServicios.buscarPorId(dni));
        mav.addObject("title", "Editar Clasificador");
        mav.addObject("action", "modificar");
        mav.addObject("roles", rolesServicio.buscarTodos());
        return mav;
    }
    
   @PostMapping("/guardar")
    //@PreAuthorize("hasAnyRole('ADMIN', 'CLASIFICADOR')")
    public String guardar(ModelMap modelo, @RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, @RequestParam String domicilio,
            @RequestParam String email, @RequestParam String instagram,
            @RequestParam Long telefono, @RequestParam String clave, @RequestParam("rol") String rolId) throws WebException, MessagingException {
        try{
            clasificadoresServicios.crearClasificador(nombre, apellido, 
                    fechaNacimiento, dni, domicilio, email, instagram, 
                    telefono, clave, rolId);
           //     emailServicio.enviarCorreo(to:email, asunto:"Registro exitoso", cuerpo:"Bienvenido a clasifica");
        }catch(WebException ex){
            modelo.put("error", ex.getMessage());
            modelo.put("dni",dni);
            modelo.put("fechaNacimiento",fechaNacimiento);
            modelo.put("nombre",nombre);
            modelo.put("apellido",apellido);
            modelo.put("domicilio",domicilio);
            modelo.put("email",email);
            modelo.put("telefono",telefono);
            modelo.put("instagram",instagram);
            modelo.put("clave",clave);
            return "clasificadores-formulario.html";
        }
                
      /*  if(resultadoValidacion.hasErrors()){
            return "clasificadores-formulario";
        }else{
        clasificadoresServicios.crearClasificador(nombre, apellido, fechaNacimiento, dni, domicilio,
                email, instagram, telefono, clave, rolId);
        //return new RedirectView("/login");
        return "login";}*/
      return "Bienvenida.html";
    }
   /* @PostMapping("/guardar")
    public String guardar(@Valid Clasificadores clasificadores, BindingResult resultadoValidacion){
        if(resultadoValidacion.hasErrors()){
            return "clasificadores-formulario";
        }else{
        clasificadoresServicios.crearClasificador(clasificadores);
        //return new RedirectView("/login");
        return "login";}
    }*/
    @PostMapping("/modificar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLASIFICADOR')")
    public RedirectView modificarClasificadores(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, @RequestParam String domicilio,
            @RequestParam String email, @RequestParam String instagram,
            @RequestParam Long telefono, @RequestParam String clave, @RequestParam("rol") String rolId, HttpSession session) throws WebException {

        clasificadoresServicios.modificarClasificadores(nombre, apellido, fechaNacimiento, dni, domicilio,
                email, instagram, telefono, clave, rolId);
        return new RedirectView("/clasificadores/perfil-clasificador/" + dni);
    }

    @GetMapping("/despedida")
    public ModelAndView despedida() {
        ModelAndView mav = new ModelAndView("despedida");
        mav.addObject("action", "volver");
        return mav;
    }

    @GetMapping("/materiales/crear")
    public ModelAndView agregarMateriales() {
        ModelAndView mav = new ModelAndView("materiales-formulario");

        mav.addObject("title", "Agregar Materiales");
        mav.addObject("action", "materiales/guardar");
        return mav;
    }

    @PostMapping("/materiales/guardar")
    public RedirectView guardar(@RequestParam Long carton, @RequestParam Long plastico, @RequestParam Long vidrio,
            @RequestParam Long latas, @RequestParam Long papel) {
        materialesServicio.crear(carton, plastico, vidrio, latas, papel);
        return new RedirectView("/perfil-clasificador/{dni}");
    }

    @GetMapping("/buscar/{nombre}")
    public ModelAndView mostrarPorNombre(@PathVariable String nombre) {
        ModelAndView mav = new ModelAndView("clasificadores-lista");
        mav.addObject("clasificadores", clasificadoresServicios.buscarPorNombre(nombre));
        return mav;
    }

    @PostMapping("/eliminar/{dni}")
    public RedirectView eliminarClasificadores(@PathVariable Long dni) {
        clasificadoresServicios.eliminarClasificadores(dni);
        return new RedirectView("/clasificadores/ver-todos");
    }

    @PostMapping("/volver")
    public RedirectView volverPerfil() {
        return new RedirectView("/perfil-clasificador/{dni}");
    }

}
