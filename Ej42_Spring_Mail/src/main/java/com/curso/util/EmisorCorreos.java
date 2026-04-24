package com.curso.util;
import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmisorCorreos {

    private final JavaMailSender javaMailSender;

    public EmisorCorreos(JavaMailSender emisorMensajes) {
        this.javaMailSender = emisorMensajes;
    }

    public void enviarCorreoSimple(String destinatario, String asunto, String contenido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@aplicacion.com"); 
        message.setTo(destinatario);
        message.setSubject(asunto);
        message.setText(contenido);

        javaMailSender.send(message);
    }

    public void enviarCorreoHtml(String destinatario, String asunto, String contenidoHtml) {
        try {
            MimeMessage correo = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(correo, true, "UTF-8");
            helper.setFrom("noreply@aplicacion.com");
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true); 

            javaMailSender.send(correo);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo HTML", e);
        }
    }
    
    public void enviarCorreoConAdjunto(String destinatario, String asunto, String contenidoHtml, String rutaAdjunto) {
        try {
            MimeMessage correo = javaMailSender.createMimeMessage();

            //El true indica multipart
            MimeMessageHelper helper = new MimeMessageHelper(correo, true, "UTF-8");

            helper.setFrom("noreply@aplicacion.com");
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true);

            // Cargamos el archivo desde el sistema de archivos
            FileSystemResource file = new FileSystemResource(new File(rutaAdjunto));
            
            // Añadimos el adjunto (nombre del archivo que verá el usuario, objeto del archivo)
            helper.addAttachment(file.getFilename(), file);

            javaMailSender.send(correo);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo con adjunto", e);
        }
    }    
    
}