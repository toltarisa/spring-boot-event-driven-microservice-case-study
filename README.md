## Event Driven Microservice Case Study

Event Driven Microservice Beginner Case Study using Spring Boot & RabbitMQ

### Technology Stack

- Java 11
- Spring Boot
- Spring Data JPA
- RabbitMQ
- Lombok
- H2 Database
- Docker (Compose)

### Running the Application

```sh
  $ docker compose up
```

### Architecture Diagram

![Architecture](https://drive.google.com/uc?id=1veWKa7U7x11VlGQ0IJ_BC4SufUd6F7Md)

### Requests

##### Create Order

POST http://localhost:9091/orders

```json
{
  "userId": 123
}
```

##### Get Order By Id

GET http://localhost:9091/orders/:orderId

##### Update Order

Order Status Types:

- order_create
- order_cancelled
- order_delivered
- order_return

PATCH http://localhost:9091/orders/:orderId?orderStatus=order_delivered

##### Email Service Example Log

```
  Notifying user with id = 123 for order status change to order_delivered with order id = 1
```
