//
//package Grupo11.ProyectoEgg.Repositorio;
//
//import Grupo11.ProyectoEgg.Entidad.Comentario;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ComentarioRepositorio extends JpaRepository <Comentario, String>{
//    
//    @Query("SELECT c FROM Comentario c WHERE c.clasificadorComentario.id = :id ORDER BY c.fechaComentarioClasificador DESC ")
//    public List<Comentario> buscarComentarioClasificador(@Param ("id") Long id);
//    
//    
//    @Query("SELECT c FROM Comentario c WHERE c.clasificadorComentario2.id = :id ORDER BY c.fechaComentarioClasificador DESC ")
//    public List<Comentario> buscarComentarioClasificadorRecibidos(@Param ("id") Long id);
//    
//    
//    @Query("SELECT c FROM Comentario c WHERE c.recuperadorComentario.id = :id ORDER BY c.fechaComentariorecuperador DESC ")
//    public List<Comentario> buscarComentarioRecuperador(@Param ("id") Long id);
//    
//    @Query("SELECT c FROM Comentario c WHERE c.clasificadorComentario2.id = :id ORDER BY c.fechaComentarioRecuperador DESC ")
//    public List<Comentario> buscarComentarioRecuperadorRecibidos(@Param ("id") Long id);
//    
//}
