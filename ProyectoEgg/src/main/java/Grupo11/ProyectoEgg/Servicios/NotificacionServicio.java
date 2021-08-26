//
//package Grupo11.ProyectoEgg.Servicios;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificacionServicio {
//    
//    @Autowired
//    private JavaMailSender mailSender;
//    
//    @Async
//    public void enviar(String cuerpo, String titutlo, String email){
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo(email);
//        mensaje.setFrom("clasifica@clasifica.com");
//        mensaje.setSubject(titutlo);
//        mensaje.setText(cuerpo);
//        
//        mailSender.send(mensaje);
//        
//    }
//    
//}
