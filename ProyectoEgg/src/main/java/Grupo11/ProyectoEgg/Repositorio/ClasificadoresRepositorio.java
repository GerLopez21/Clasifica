package Grupo11.ProyectoEgg.Repositorio;

import Grupo11.ProyectoEgg.Entidad.Clasificadores;
import Grupo11.ProyectoEgg.Entidad.Rol;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificadoresRepositorio extends JpaRepository<Clasificadores, Long> {

    @Modifying
    @Query("UPDATE Clasificadores c SET c.nombre = :nombre, c.apellido = :apellido, c.fechaNacimiento = :fechaNacimiento,"
            + " c.domicilio = :domicilio, c.email = :email, c.instagram = :instagram,"
            + "c.telefono = :telefono,"
            + "c.clave = :clave, c.rol =:rol WHERE c.dni = :dni")
    void modificarClasificadores(@Param("nombre") String nombre, @Param("apellido") String apellido,
            @Param("fechaNacimiento") Date fechanacimiento, @Param("domicilio") String domicilio,
            @Param("email") String email, @Param("instagram") String instagram,
            @Param("telefono") Long telefono,
            @Param("dni") Long dni, @Param("clave") String clave, @Param("rol") Rol rol);

    @Query("SELECT c FROM Clasificadores c WHERE c.nombre = :nombre")
    List<Clasificadores> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Clasificadores c WHERE c.email = :email")
    Clasificadores buscarPorEmail(@Param("email") String email);

}
