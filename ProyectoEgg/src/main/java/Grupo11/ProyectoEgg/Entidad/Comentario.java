//
//package Grupo11.ProyectoEgg.Entidad;
//
//import java.util.Date;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Data
//public class Comentario {
//    
//   @Id 
//   @GeneratedValue(generator = "uuid")
//   @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
//   
//    @Temporal(TemporalType.DATE)
//    private Date fechaComentarioClasificador;
//    
//    @Temporal(TemporalType.DATE)
//    private Date fechaComentarioRecuperador;
//    
//    @ManyToOne
//    private Clasificadores clasificadorComentario;
//    
//    @ManyToOne
//    private Clasificadores clasificadorComentario2;
//    
//    @ManyToOne
//    private Recuperador recuperadorComentario;
//    
//    @ManyToOne
//    private Recuperador recuperadorComentario2;  
//    
//}
