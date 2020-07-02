# Scheduling Transfer Service

```
Program responsible for scheduling financial transfers
```

| Technology  | Version | Motivation                                                                      |
|-------------|---------|---------------------------------------------------------------------------------|
| Java        | 11      | Last version stable version                                                     |
| Spring Boot | 2.3.1   | Framework to facilitate microservice configuration, development and publication |
| Swagger     | 2.9.2   | Library that helps in creating my microservice documentation                    |
| JUnit       | 4.12    | Library that helps in the creation of unit tests                                |
| Redis       | 5.0.3   | Database to help with data caching                                              |

```
Considerations:
- FakeDataBase Class: This class aims to only popularize the database.
- Rates: I preferred to consult the rates in the database to avoid over coding, even using the Strategy pattern the work would be unproductive.
```

```
Instructions:

- Clone repository in your machine. Use the command on terminal: git clone https://github.com/victorrmatos19/githubinterface.git
- Open project in your IDE, execute the commando on terminal: mvn clean install
- Run project.

*Ps: Make sure you have Maven and Git installed*

Redis:

- Execute the commando on terminal to run Redis container: docker run -it --name redis -p 6379:6379 redis:5.0.3

*Ps: Make sure you have docker installed*
```

- Below is the collection for testing the application:

[Postman Collection](https://www.getpostman.com/collections/f425a7cc682e78528c74)

###### Author : Victor Rodrigues de Matos