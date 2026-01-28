# 📊 Fitness History Microservice

The **Fitness History Service** is a specialized microservice designed to track, store, and manage historical data for the Fitness Club ecosystem. Built with **Spring Boot 3.4** and **Java 21**, it provides a high-performance persistence layer for workout logs.

---

## 🏗️ Microservice Role

In a microservice architecture, this service acts as the **System of Record** for all historical events.

* **Activity Tracking:** Records individual workout sessions.
* **Decoupled Persistence:** Offloads historical data from the main application to maintain primary database performance.

---

## 🛠️ Technical Stack

| Component | Technology |
| --- | --- |
| **Backend** | Spring Boot 3.4.0 |
| **Language** | Java 21 (LTS) |
| **Persistence** | Spring Data JPA |
| **Database** | MySQL |
| **Utilities** | Lombok (Annotation Processing) |
| **Testing** | Spring Boot Starter Test |

---

## ⚙️ Configuration & Setup

### Requirements

* **JDK 21**
* **Maven 3.9+**
* **MySQL 8.x**

### Local Setup

1. **Clone the service:**
```bash
git clone https://github.com/your-username/fitness-history-service.git

```


2. **Database Configuration:**
Ensure your `application.properties` points to your history-specific database:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fitness_history_db
spring.datasource.username=db_user
spring.datasource.password=db_password
spring.jpa.hibernate.ddl-auto=update

```


3. **Build & Package:**
```bash
mvn clean package

```


4. **Run:**
```bash
java -jar target/fitness-history-service-0.0.1-SNAPSHOT.jar

```



---

## 📡 API Usage Example

The service typically receives data from the main application via REST:

**`POST /api/history/record`**

```json
{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "workoutName": "Morning Cardio",
  "duration": 45
}
```

---

## 🧪 Development Notes

This service is configured with the `maven-compiler-plugin` to correctly handle Lombok's annotation processor paths during the build cycle, ensuring that Getters, Setters, and Builders are generated seamlessly.
