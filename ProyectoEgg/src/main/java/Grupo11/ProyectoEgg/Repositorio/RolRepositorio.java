package Grupo11.ProyectoEgg.Repositorio;

import Grupo11.ProyectoEgg.Entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepositorio extends JpaRepository<Rol,String> { 
    
    // comentar si se borra la base de datos
    @Query("SELECT r FROM Rol r WHERE r.nombre = :nombre")
    Rol buscarPorNombre(@Param ("nombre") String nombre);
    
}