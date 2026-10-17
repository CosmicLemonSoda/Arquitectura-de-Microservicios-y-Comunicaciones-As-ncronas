package co.edu.unicauca.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.exchange.name:saberpro_exchange}")
    private String exchangeName;

    @Value("${rabbit.queue.name:question.review.queue}")
    private String queueName;

    @Value("${rabbit.routing.key:question_events}")
    private String routingKey;

    @Bean
    public Queue notificationQueue() {
        return new Queue(queueName, true); // durable = true
    }

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(routingKey);
    }
}