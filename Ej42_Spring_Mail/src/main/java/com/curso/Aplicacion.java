package com.curso;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.curso.util.EmisorCorreos;

@SpringBootApplication
public class Aplicacion {

	//
	//docker run -d --name mailpit -p 1025:1025 -p 8025:8025 axllent/mailpit
	//
	//http://localhost:8025
	//
	
	
	public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }

    @Bean
    CommandLineRunner run(EmisorCorreos emailService) {
        return args -> {
            System.out.println("=========================================================");

            emailService.enviarCorreoSimple(
                "usuario1@triquitraun.com", 
                "Gana mucha pasta", 
                "Hola, soy el secretario de un príncipe Nigeriano que..."
            );
            System.out.println("Correo simple enviado...");

            String htmlContent = """
                <h1>¡Ha ganado un premio!</h1>
                <p>Estimado amigo<b>ha ganado usted un premio</b> sensacional.</p>
                <p>Pásese a recoger el Primer Premio Montgomery Burns Por Su Sobresaliente Labor En El Campo De La Excelencia.
                <hr>
                <a href='http://localhost:8025' style='background: #007bff; color: white; padding: 10px; text-decoration: none; border-radius: 5px;'>
                    Ver en Mailpit
                </a>
            	""";
            
            emailService.enviarCorreoHtml(
                "usuario2@triquitraun.com", 
                "Un premio para usted", 
                htmlContent
            );
            System.out.println("Correo HTML enviado...");
            
            
            String htmlContent2 = """
                <h1>Querido receptor de este mensaje:</h1>
                <p>Adjunto hay un inocuo jar ejecutable que rogamos descargue y ejecute con permisos de administrador.</p>
                <p>Sin duda pasará usted un rato excelente haciéndolo.</p>
                <p>Un cordial saludo.</p>
                """;
                
            emailService.enviarCorreoConAdjunto(
	                "usuario3@triquitraun.com", 
	                "Aplicación gratuita", 
	                htmlContent2,
	                "D:/borrar/inocuo.jar"
	            );
            System.out.println("Correo con adjunto enviado...");            
            
            
            System.out.println("FIN");
        };
    }

}
