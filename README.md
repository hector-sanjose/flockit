# Examen Flock IT
Resolución de examen técnico para Flock IT

## Tabla de contenidos
* [Tecnologías utilizadas](#Tecnologías-utilizadas)
* [Set Up](#setup)
* [Tests](#tests)

## Tecnologías utilizadas
Este examen fue resuelto con:
* Java 1.8
* Spring Boot 2.6.8
* JPA
* H2 (Base de datos en memoria)
* OpenAPI 3 (Swagger) 
* Maven 3.8.6
	
## Set Up
Luego de clonar el repositorio, para correr el código y probarlo, ejecutar la siguiente línea desde el directorio `/examen`:

```
mvn spring-boot:run
```

Una vez levantado Spring Boot ir a la URL `http://localhost:8080/swagger-ui.html` para probar los diferentes endpoints. 

Para el endpoint `/login` puede utilizar el email `hector.sanjose@gmail.com` y el password `admin`.

Para el endpoint `/provinces/{name}/coordinates` puede utilizar cualquier nombre de provincia.

## Tests
Para correr los tests, ejecutar la siguiente línea desde el directorio `/examen`:

```
mvn test
```
