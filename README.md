# spring-boot-quartz-postgres-experiment
Demo project for experimenting quartz in jdbc job store with postgresql in spring boot

# Project Structure
1. Java version 18
2. Spring Boot 2.6.7
3. Postgresql

# Run Project
1. Run docker compose file (docker-compose.yml) available on classpath using `docker-compose -f docker-compose.yml up`
2. Check whether you have a new database created in postgres named `spring-boot-quartz-postgres-experiment`. If not, create that.
3. Build the project using `mvn clean package`
4. Run the project `java -jar spring-boot-quartz-postgres-experiment-0.0.1-SNAPSHOT.jar`
