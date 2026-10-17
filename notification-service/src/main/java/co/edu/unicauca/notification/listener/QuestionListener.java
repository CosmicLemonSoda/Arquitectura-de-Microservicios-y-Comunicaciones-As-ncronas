package co.edu.unicauca.notification.listener;

import co.edu.unicauca.notification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QuestionListener {

    private final EmailService emailService;

    public QuestionListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "question.review.queue")
    public void receiveMessage(String message) {
        System.out.println("[RABBITMQ CONSUMER] Mensaje recibido en Notification Service: " + message);
        emailService.sendEmailNotification(message);
    }
}