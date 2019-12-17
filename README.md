# API Star Wars

### Requisitos
- Java 11.0.5 LTS
- Kotlin 1.3.61
- Spring Boot 2.2.2
- Gradle 6.0.1
- Tomcat
- MongoDB
- IDE IntelliJ IDEA Ultimate

### Configurações

Informe a URI do banco MongoDB no arquivo: `/resources/application.properties`

### Build do projeto

No Windows: `gradlew.bat build`

No Linux: `./gradlew build`

### Execução do projeto

Execute o comando: `java -jar ./build/libs/swapi-0.0.1-SNAPSHOT.war`

### API

URL: `http://localhost:8080/api/planet`

Documentação: `http://localhost:8080/swagger-ui.html`