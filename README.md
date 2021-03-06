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
| MySql       | 5.6     | Main Database                                                                   | 

```
Considerations:
- Rates: I preferred to consult the rates in the database to avoid over coding, even using the Strategy pattern the work would be unproductive.
```

```
Instructions:

Redis and MySql:
- Execute the command on terminal to run Redis and MySql: docker-compose up
*Ps: At the root of the project*
MySql:
- Execute the command on terminal to run MySQL bash: docker exec -it container-mysql bash
- In the terminal type : "mysql -uroot -p", type password "root" and run the script contained in the 'script.sql" file

*Ps: Make sure you have docker installed*

- Clone repository in your machine. Use the command on terminal: git clone https://github.com/victorrmatos19/githubinterface.git
- Open project in your IDE, execute the command on terminal: mvn clean install
- Run project.

*Ps: Make sure you have Maven and Git installed*
```

- Below is the collection for testing the application:

[Postman Collection](https://www.getpostman.com/collections/f425a7cc682e78528c74)

###### Author : Victor Rodrigues de Matos