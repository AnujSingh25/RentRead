# Configuration required
- Java 17
- MySQL

# steps for run

- rename application.properties located - src/main/resources directory
   spring.datasource.url = jdbc:mysql://localhost:3306/rent_read
   spring.datasource.username = your username
   spring.datasource.password = your password

- cmd - ./gradlew bootrun