package Grupo11.ProyectoEgg.Servicios;

import Grupo11.ProyectoEgg.Entidad.Clasificadores;
import Grupo11.ProyectoEgg.Entidad.Materiales;
//import Grupo11.ProyectoEgg.Entidad.Rol;
import Grupo11.ProyectoEgg.Entidad.Ruta;
import Grupo11.ProyectoEgg.Errores.WebException;
import Grupo11.ProyectoEgg.Repositorio.ClasificadoresRepositorio;
import Grupo11.ProyectoEgg.Repositorio.MaterialesRepositorio;
import Grupo11.ProyectoEgg.Validaciones.Validaciones;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Grupo11.ProyectoEgg.Repositorio.RolRepositorio;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ClasificadoresServicios implements UserDetailsService {

    @Autowired
    private Validaciones validaciones;

    @Autowired
    private ClasificadoresRepositorio clasificadoresRepositorio;

    @Autowired
    private RutaServicio rutaServicio;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private MaterialesRepositorio materialesRepositorio;
    @Autowired
    private EmailServicio emailServicio;
//    @Autowired
//    private NotificacionServicio notificacionServicio;
//    @Autowired
//    private FotoServicio fotoServicio;
    @Transactional
   public void crearClasificador(/*MultiparFile archivo,*/String nombre, String apellido, Date FechaNacimiento, Long dni, String domicilio,
       String email, String instagram, Long telefono, String clave, String rolId) throws WebException, MessagingException {
   
        validar(nombre, apellido, domicilio, email, instagram, clave);
       Clasificadores clasificadores = new Clasificadores();
        
         clasificadores.setNombre(nombre);
        clasificadores.setApellido(apellido);
        clasificadores.setFechaNacimiento(FechaNacimiento);
        clasificadores.setDni(dni);
        clasificadores.setDomicilio(domicilio);
        //clasificadores.setRol(rolRepositorio.buscarPorNombre("CLASIFICADOR"));
        clasificadores.setRol(rolRepositorio.findById(rolId).orElse(null));

//        do {
//           
//            if (validaciones.validarEmail(email) == true) {
//                System.out.println("EL EMAIL SE CREO CORRECTAMENTE");
//                clasificadores.setEmail(email);
//            }
//        } while (!validaciones.validarEmail(email) == true);
        clasificadores.setEmail(email);
        clasificadores.setInstagram(instagram);
        clasificadores.setTelefono(telefono);
        clasificadores.setFechaCreacionUsuario(new Date());
        clasificadores.setClave(encoder.encode(clave));
        emailServicio.enviarCorreoAsincrono(email, "Correo de bienvenida", "Bienvenido a clasifica. Gracias por registrarte");
//      Foto foto = fotoServicio.guardar(archivo);
//      clasificadores.setFoto(Foto);
        clasificadoresRepositorio.save(clasificadores);

//      notificacionServicio.enviar("Bienvenido a Clasifica", "Clasifica Recuperadores y Gestion de Basura", clasificadores.getEmail());
    }

   @Transactional
    public void modificarClasificadores(/*MultiparFile archivo,*/String nombre, String apellido, Date FechaNacimiento, Long dni, String domicilio,
            String email, String instagram, Long telefono, String clave, String rolId) throws WebException {

        Clasificadores clasificadores = new Clasificadores();

       validar(nombre, apellido, domicilio, email, instagram, clave);

        Optional<Clasificadores> respuesta = clasificadoresRepositorio.findById(dni);

        if (respuesta.isPresent()) {

            clasificadores.setNombre(nombre);
            clasificadores.setApellido(apellido);
            clasificadores.setFechaNacimiento(FechaNacimiento);
            clasificadores.setDni(dni);
            clasificadores.setDomicilio(domicilio);
            clasificadores.setRol(rolRepositorio.findById(rolId).orElse(null));
            clasificadores.setEmail(email);
            clasificadores.setInstagram(instagram);
            clasificadores.setTelefono(telefono);
            clasificadores.setFechaCreacionUsuario(new Date());
            clasificadores.setClave(encoder.encode(clave));
            
            clasificadoresRepositorio.save(clasificadores);
//            clasificadoresRepositorio.modificarClasificadores(nombre, apellido, FechaNacimiento, domicilio, email, instagram, 
//                telefono, dni, clave, rolRepositorio.findById(rolId).orElse(null));

        } else {
            throw new WebException("NO SE ENCUENTRA EL CLASIFICADOR SOLICITADO");
        }

    }

    private void validar(String nombre, String apellido, String domicilio, String email, String instagram, String clave) throws WebException {
            
        if (nombre == null || nombre.isEmpty()) {
            throw new WebException("EL NOMBRE DEL CLASIFICADOR NO PUEDE SER NULO");

        }

        if (apellido == null || apellido.isEmpty()) {
            throw new WebException("EL APELLIDO DEL CLASIFICADOR NO PUEDE SER NULO");

        }

        if (domicilio == null || domicilio.isEmpty()) {
            throw new WebException("EL DOMICILIO DEL CLASIFICADOR NO PUEDE SER NULO");

        }

        if (email == null || email.isBlank()) {
            throw new WebException("EL MAIL DEL CLASIFICADOR NO PUEDE SER NULO");

        }

        if (instagram == null || instagram.isEmpty()) {
            throw new WebException("EL INSTAGRAM DEL CLASIFICADOR NO PUEDE SER NULO");

        }

        if (clave == null || clave.isEmpty() || clave.length() <= 4) {
            throw new WebException("LA CLAVE DEL CLASIFICADOR NO PUEDE SER NULO Y TIENE QUE TENER MAS DE 4 DIGITOS");

        }
    }

    @Transactional
    public void eliminarClasificadores(Long dni) {
        clasificadoresRepositorio.deleteById(dni);
    }

    @Transactional
    public Clasificadores buscarPorId(Long dni) {
        Optional<Clasificadores> clasificadoresOpcional = clasificadoresRepositorio.findById(dni);
        return clasificadoresOpcional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Clasificadores> buscarTodos() {
        return clasificadoresRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public List<Clasificadores> buscarPorNombre(String nombre) {
        return clasificadoresRepositorio.buscarPorNombre(nombre);
    }

    @Transactional
    public void AgregarRuta(Long dni, String idRuta, Materiales materiales) {
        Clasificadores userDni = buscarPorId(dni);
        Ruta IDdeLaRuta = rutaServicio.buscarPorId(idRuta);

        userDni.setRuta(IDdeLaRuta);
        userDni.setMateriales(materialesRepositorio.save(materiales));
        IDdeLaRuta.setCantidadClasificadores(IDdeLaRuta.getCantidadClasificadores()+1);
    
        clasificadoresRepositorio.save(userDni);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Clasificadores clasificadores = clasificadoresRepositorio.buscarPorEmail(email);

        if (clasificadores == null) {
            throw new UsernameNotFoundException("No hay un usuario con email " + email);
        }

        GrantedAuthority rol = new SimpleGrantedAuthority("ROLE_" + clasificadores.getRol().getNombre());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("dni", clasificadores.getDni());
//        session.setAttribute("nombre", clasificadores.getNombre());
//        session.setAttribute("email", clasificadores.getEmail());
//        session.setAttribute("rol", clasificadores.getRol().getNombre());

        User user = new User(clasificadores.getEmail(), clasificadores.getClave(), Collections.singletonList(rol));

        return user;
    }

}
