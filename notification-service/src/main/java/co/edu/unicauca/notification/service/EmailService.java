package co.edu.unicauca.notification.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmailNotification(String messageContent) {
        // Simulación de envío de correo electrónico institucional
        System.out.println("==================================================");
        System.out.println("[NOTIFICACIÓN EMAIL] Enviando correo electrónico...");
        System.out.println("Contenido: " + messageContent);
        System.out.println("Estado: Enviado exitosamente a los involucrados.");
        System.out.println("==================================================");
    }
}