# 🎓 StudentApp — Spring Boot + MySQL + Docker

A simple **Student Management REST API** built with **Spring Boot, Spring Data JPA, MySQL, Docker, and Docker Compose**.

This project was created to learn how to **Dockerize a Spring Boot application** and run both the application and MySQL database together using **Docker Compose**.

---

## 🚀 Tech Stack

| Technology          | Purpose                      |
| ------------------- | ---------------------------- |
| ☕ Java              | Programming Language         |
| 🌱 Spring Boot      | Backend Framework            |
| 🌐 Spring REST      | REST API                     |
| 🗃️ Spring Data JPA | Database Operations          |
| 🐘 Hibernate        | ORM                          |
| 🐬 MySQL            | Database                     |
| 🐳 Docker           | Application Containerization |
| 🔗 Docker Compose   | Multi-container Management   |
| 🛠️ Maven           | Build Tool                   |

---

## 🏗️ Project Architecture

```text
                    Docker Compose
                         │
             ┌───────────┴───────────┐
             │                       │
             ▼                       ▼
      ┌──────────────┐        ┌──────────────┐
      │ Spring Boot  │        │    MySQL     │
      │  StudentApp  │───────▶│   Database   │
      │              │        │              │
      │   Port 8080  │        │   Port 3306  │
      └──────────────┘        └──────────────┘
             │
             ▼
       REST Endpoints
             │
             ▼
        Student Data
```

---

## ✨ Features

* ✅ Create a student
* ✅ Get all students
* ✅ Store student data in MySQL
* ✅ Spring Data JPA integration
* ✅ REST API
* ✅ Dockerized Spring Boot application
* ✅ MySQL running in Docker
* ✅ Docker Compose for managing multiple containers
* ✅ Environment-based database configuration

---

## 📂 Project Structure

```text
StudentApp/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/studentapp/
│       │       ├── Student.java
│       │       ├── StudentRepo.java
│       │       ├── StudentController.java
│       │       └── StudentAppApplication.java
│       │
│       └── resources/
│           └── application.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 🐳 Docker Configuration

The `Dockerfile` creates an image for the Spring Boot application.

Example:

```dockerfile
FROM openjdk:22-jdk

ADD target/studentapp.jar studentapp.jar

ENTRYPOINT ["java", "-jar", "studentapp.jar"]
```

Docker is used to package the Spring Boot application together with everything required to run it.

---

# 🔗 Docker Compose

Docker Compose is used to run **Spring Boot + MySQL** together.

Example:

```yaml
services:

  studentapp:
    build: .
    container_name: studentapp

    ports:
      - "8080:8080"

    depends_on:
      - mysql

    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/studentdocker
      SPRING_DATASOURCE_USERNAME: aashish
      SPRING_DATASOURCE_PASSWORD: root123

    networks:
      - my-network


  mysql:
    image: mysql:8.0
    container_name: mysql

    environment:
      MYSQL_DATABASE: studentdocker
      MYSQL_USER: aashish
      MYSQL_PASSWORD: root123
      MYSQL_ROOT_PASSWORD: root123

    ports:
      - "3306:3306"

    networks:
      - my-network


networks:
  my-network:
```

> 🔐 For a real production project, database credentials should be stored using secrets or environment files rather than committing passwords directly to GitHub.

---

# 🗄️ Student Entity

The application uses a simple `Student` entity.

```java
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
}
```

---

# 📦 Repository

Spring Data JPA is used for database operations.

```java
@Repository
public interface StudentRepo
        extends JpaRepository<Student, Integer> {
}
```

Because `JpaRepository` is extended, common database operations are automatically available:

```text
findAll()
findById()
save()
deleteById()
count()
```

---

# 🌐 REST API

## Get All Students

```http
GET /getStudents
```

Example:

```text
http://localhost:8080/getStudents
```

Response:

```json
[
  {
    "id": 1,
    "name": "Arahuk",
    "age": 65
  }
]
```

---

## Add Student

```http
GET /add/Student
```

Example:

```text
http://localhost:8080/add/Student
```

This creates a student and stores the data in MySQL.

> For a production REST API, this endpoint should ideally use `POST` rather than `GET`.

---

# ▶️ Run the Project with Docker

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/StudentApp.git
```

```bash
cd StudentApp
```

## 2️⃣ Build the Project

Make sure Maven generates the JAR file:

```bash
mvn clean package
```

The JAR will be generated inside:

```text
target/
```

## 3️⃣ Start Docker Compose

```bash
docker compose up --build
```

Docker will:

1. Build the Spring Boot image
2. Pull the MySQL image
3. Create the containers
4. Create the Docker network
5. Start MySQL
6. Start the Spring Boot application

---

# 🛑 Stop the Application

```bash
docker compose down
```

To stop containers and remove the database volume as well:

```bash
docker compose down -v
```

⚠️ `-v` removes the database volume, so stored MySQL data may be deleted.

---

# 🔍 Check Running Containers

```bash
docker ps
```

You should see containers similar to:

```text
studentapp
mysql
```

---

# 📋 View Application Logs

```bash
docker compose logs studentapp
```

MySQL logs:

```bash
docker compose logs mysql
```

Follow logs continuously:

```bash
docker compose logs -f
```

---

# 🧪 Testing

You can test the APIs using:

* Postman
* Browser
* cURL

Example:

```bash
curl http://localhost:8080/getStudents
```

---

# 🧠 What I Learned

This project helped me understand the fundamentals of **containerizing a Spring Boot application**.

### Docker

* Docker images
* Docker containers
* Dockerfile
* Port mapping
* Container networking
* Docker commands
* Building application images

### Docker Compose

* Multi-container applications
* Service configuration
* Container networking
* Environment variables
* Service dependencies
* Running Spring Boot and MySQL together

### Spring Boot

* REST Controllers
* Spring Data JPA
* Hibernate
* MySQL integration
* Entity and Repository layers

---

# 🔄 Application Flow

```text
Client
  │
  │ HTTP Request
  ▼
Spring Boot Container
  │
  │ Spring Data JPA
  ▼
Hibernate
  │
  │ JDBC
  ▼
MySQL Container
  │
  ▼
Student Database
```

---

# 🎯 Project Goal

The main goal of this project was to understand how a **Spring Boot backend and MySQL database can be containerized and run together using Docker Compose**.

Instead of installing and configuring every dependency manually, Docker provides a consistent environment that can be started with:

```bash
docker compose up --build
```

---

# 👨‍💻 Author

**Aashish Kumar**

Java & Spring Boot Developer | CSE Student

### Skills

```text
Java • Spring Boot • Spring Data JPA • Hibernate
REST APIs • MySQL • Redis • Kafka
Docker • Docker Compose • Git • GitHub
```

---

⭐ If you found this project useful, consider giving it a **star**!

---

## 📌 Key Takeaway

> **Build once, run anywhere. 🐳**

This project demonstrates my practical understanding of **Spring Boot application development and Docker containerization**, including running a backend service and MySQL database together using **Docker Compose**.
