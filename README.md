#  Book Manager App

A full-stack CRUD web application for managing a list of books. Built with **Angular** (frontend), **Spring Boot** (backend), and **MySQL** (database). Deployed on **Render** with a public live link.

##  Live Demo

 [https://book-manager-app.onrender.com](https://book-manager-app.onrender.com)

---

##  Features

 Add a new book  
 View list of books with pagination  
 Edit existing book info  
 Delete a book  
 Search books by title or author  
 Integrated with MySQL database (hosted online)  
 Fully responsive UI with Angular  
 REST API backend with Spring Boot  

---

##  Tech Stack

| Layer         | Technology             |
|---------------|------------------------|
| **Frontend**  | Angular, TypeScript, HTML, CSS |
| **Backend**   | Spring Boot, Java      |
| **Database**  | MySQL (via FreeSQLDatabase) |
| **Deployment**| Render.com             |
| **Build Tool**| Maven                  |

---

##  How to Run Locally

###  Backend (Spring Boot)
1. Clone the repo:
    ```bash
    git clone https://github.com/junasaricky/book-manager-app.git
    cd book-manager-app
2. Update application.properties for local database:
    spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
3. Run the backend:
    ./mvnw spring-boot:run

###  Frontend (Angular)
1. Navigate to the Angular folder:
    cd book-app
2. Install dependencies:
    npm install
3. Run Angular dev server:
    ng serve
4. Open in browser:
    http://localhost:4200

###  Build for Production (Angular → Spring Boot)
1. Build Angular for production:
    ng build --configuration production
2. Copy the Angular build files into Spring Boot’s static folder:
    xcopy /E /I /Y "dist\book-app\*" "src\main\resources\static\"
3. Build Spring Boot:
    ./mvnw clean package
4. Run the final app:
    java -jar target/springbootdemo-0.0.1-SNAPSHOT.jar
5. Open in browser:
    http://localhost:8080
    
##  Deployment (Render)

This app is deployed to **Render** using **Docker** and **online MySQL**.

###  Environment Variables (Render)

| Key           | Value                        |
|---------------|------------------------------|
| `DB_HOST`     | `sql12.freesqldatabase.com`  |
| `DB_PORT`     | `3306`                       |
| `DB_NAME`     | `your-db-name`               |
| `DB_USERNAME` | `your-db-user`               |
| `DB_PASSWORD` | `your-db-password`           |

---

##  About the Developer

**Ricky Junasa**  
📍 Philippines  
💻 Passionate about building full-stack projects  
📧 [junasaricky@gmail.com](mailto:junasaricky@gmail.com)  
🔗 [GitHub](https://github.com/junasaricky)

