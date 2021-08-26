package Grupo11.ProyectoEgg.Servicios;

import Grupo11.ProyectoEgg.Entidad.Clasificadores;
import Grupo11.ProyectoEgg.Entidad.Recuperador;
import Grupo11.ProyectoEgg.Entidad.Ruta;
import Grupo11.ProyectoEgg.Repositorio.ClasificadoresRepositorio;
import Grupo11.ProyectoEgg.Repositorio.RecuperadorRepositorio;
import Grupo11.ProyectoEgg.Repositorio.RutaRepositorio;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaServicio {

    @Autowired
    private RutaRepositorio rutaRepositorio;

    @Autowired
    private RecuperadorRepositorio recuperadorRepositorio;
    
    @Autowired
    private ClasificadoresRepositorio clasificadoresRepositorio;
    
    @Transactional
    public void crearRuta(String departamento, Date fecha, int cantidadClasificadores, Double valorRuta, Recuperador recuperadores,String disponibles) {
        Ruta ruta = new Ruta();
        ruta.setDepartamento(departamento);
        ruta.setFecha(fecha);
        ruta.setCantidadClasificadores(cantidadClasificadores);
        ruta.setValorRuta(valorRuta);
        ruta.setRecuperador(recuperadores);
        ruta.setDisponibles("Activo");
        rutaRepositorio.save(ruta);
    }

    @Transactional
    public void modifcicarRuta(String id, String departamento, Date fecha, int cantidadClasificadores, Double valorRuta, Recuperador recuperador, String disponibles) {
//        Ruta rutaOpcional = rutaRepositorio.findById(id).get();
//        rutaOpcional.setDepartamento(departamento);
//        rutaOpcional.setFecha(fecha);
//        rutaOpcional.setCantidadClasificadores(cantidadClasificadores);
//        rutaOpcional.setValorRuta(valorRuta);
//        rutaOpcional.setRecuperadores(recuperadores);
//        rutaRepositorio.save(rutaOpcional);
//        
//        for (Recuperador recuperador : recuperadores) {
//            rutaOpcional.setRecuperadores(null);
//            
//        }
    
              rutaRepositorio.modificar(id, departamento, fecha, cantidadClasificadores, valorRuta, recuperador, disponibles);

    }

    @Transactional
    public List<Ruta> buscarPorDepartamento(String departamento) {
        return rutaRepositorio.buscarPorDepartamento(departamento);
    }
    
    @Transactional 
    public List<Ruta> mostrarDisponibles(String disponibles){
        return rutaRepositorio.buscarDisponibles(disponibles);
    }
    
    @Transactional
    public List<Ruta> buscarTodos() {
        return rutaRepositorio.findAll();
    }

    @Transactional
    public Ruta buscarPorId(String id) {
        Optional<Ruta> rutaOpcional = rutaRepositorio.findById(id);
        return rutaOpcional.orElse(null);
    }

    @Transactional
    public void eliminarRuta(String id) {
        rutaRepositorio.deleteById(id);
    }
    
    @Transactional
    public void agregarClasificador(String id, Clasificadores clasificadores){
        Ruta ruta = buscarPorId(id);
        
        rutaRepositorio.save(ruta);
    }
   
}
