package Grupo11.ProyectoEgg.Servicios;

import Grupo11.ProyectoEgg.Entidad.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Grupo11.ProyectoEgg.Repositorio.RolRepositorio;

@Service

public class RolServicio {
    
    @Autowired
    private RolRepositorio rolesRepositorio;
    
    
    @Transactional
    public void crear(String nombre){
        
        Rol rol = new Rol();
        rol.setNombre(nombre);
        rolesRepositorio.save(rol);
        
        
    }
    @Transactional
    public List<Rol> buscarTodos(){
        return rolesRepositorio.findAll();
    }
   
    
    // comentar si se borra la base de datos
   @Transactional(readOnly = true)
    public Rol buscarPorRolId(String nombre) {
        return rolesRepositorio.buscarPorNombre(nombre);
    }

}

