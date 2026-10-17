# Sistema SaberPro - Arquitectura de Microservicios

Este repositorio contiene la implementación del sistema **SaberPro**, desarrollado como parte del laboratorio de arquitectura de microservicios. El proyecto implementa un monorepo compuesto por **7 microservicios independientes** que se comunican tanto de forma síncrona (HTTP/REST) como asíncrona mediante mensajes orientados a eventos (RabbitMQ).

---

## Stack Tecnológico
* **Lenguaje:** Java 25.0.1
* **Framework:** Spring Boot 3.5.6
* **Gestor de Dependencias:** Apache Maven
* **Mensajería:** RabbitMQ (Docker)
* **Comunicación Síncrona:** RestTemplate (HTTP)
* **Manejo de Datos:** POJOs explícitos (sin Lombok para garantizar compatibilidad de bytecode).

---

## Puertos y Distribución de Microservicios

El sistema está dividido en 7 microservicios desacoplados, ejecutándose cada uno en su propio puerto:

| Microservicio | Puerto | Descripción |
| :--- | :---: | :--- |
| **`user-service`** | `8081` | Gestión, registro y consulta de usuarios y roles del sistema. |
| **`validation-service`** | `8082` | Validación síncrona de reglas de negocio para las preguntas. |
| **`question-service`** | `8083` | Banco de preguntas, creación y gestión del ciclo de vida de ítems. |
| **`notification-service`** | `8084` | Consumidor asíncrono de eventos de RabbitMQ para alertas de revisión. |
| **`lifecycle-service`** | `8085` | Control de estados y flujos operativos del sistema SaberPro. |
| **`review-service`** | `8086` | Módulo de revisión y calificación de ítems por parte de pares. |
| **`exam-service`** | `8087` | Ensamblaje, estructuración y gestión de exámenes finales. |

---

## Patrones de Comunicación

1. **Comunicación Síncrona (HTTP - REST):**
   * El **`question-service`** (`:8083`) realiza peticiones `POST` síncronas al **`validation-service`** (`:8082`) en la ruta `/validation/validate` para garantizar que toda pregunta cumpla con los criterios antes de ser persistida.
2. **Comunicación Asíncrona (Eventos - RabbitMQ):**
   * Al enviar una pregunta a revisión (`PUT /questions/{id}/submit`), el **`question-service`** publica un mensaje hacia un Exchange/Cola de RabbitMQ, el cual es consumido en tiempo real por el **`notification-service`** (`:8084`) sin bloquear el hilo principal.

---

## Instrucciones de Ejecución

### 1. Levantar RabbitMQ con Docker
Para habilitar la mensajería asíncrona, ejecuta el siguiente contenedor en tu terminal:
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management