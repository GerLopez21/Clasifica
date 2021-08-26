//package Grupo11.ProyectoEgg.Servicios;
//
//import Grupo11.ProyectoEgg.Entidad.Clasificadores;
//import Grupo11.ProyectoEgg.Entidad.Comentario;
//import Grupo11.ProyectoEgg.Entidad.Recuperador;
//import Grupo11.ProyectoEgg.Errores.ErrorServicio;
//import Grupo11.ProyectoEgg.Repositorio.ClasificadoresRepositorio;
//import java.util.Date;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ComentarioServicio {
//
//    @Autowired
//    private ClasificadoresRepositorio clasificadoresRepositorio;
//
//    @Autowired
//    private Clasificadores clasificadores;
//
//    public void comentarioClasficador(Long dni, String iDClasificadorComentario, String iDClasificadorComentario2) throws ErrorServicio {
//
//        Comentario comentario = new Comentario();
//
//        comentario.setFechaComentarioClasificador(new Date());
//
//        Optional<Clasificadores> respuestaClasificador = clasificadoresRepositorio.findById(dni);
//
//        if (respuestaClasificador.isPresent()) {
//
//            Clasificadores clasificador = respuestaClasificador.get();
//
//        } else {
//            throw new ErrorServicio("EL CLASIFICADOR NO TIENE COMENTARIOS");
//        }
//
//    }
//
//}
