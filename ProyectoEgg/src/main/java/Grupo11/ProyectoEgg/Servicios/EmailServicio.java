/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grupo11.ProyectoEgg.Servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {
    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String from;
 /*   public void enviarCorreo(String to,  String asunto, String cuerpo) throws MessagingException{
        System.out.println("Enviando correo a " + to);
        String[] para = {to};
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(asunto);
        helper.setText(cuerpo);
        sender.send(message);
        }*/
    public void enviarCorreoAsincrono(String to,  String asunto, String cuerpo){
        new Thread(()->{
            try {
            System.out.println("Enviando correo a " + to);
            String[] para = {to};
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(asunto);
            helper.setText(cuerpo);
            sender.send(message);
            } catch (MessagingException ex) {
                System.out.println("ERROR AL ENVIAR CORREO A " + to);
            }
        }).start();
        
    }
}
