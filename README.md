# Concepts Learning


## Structure
- It can be used something like:
- packages: main
  |
  |  - controllers
  |  - models
  |  - services

- But controllers will need to communicate with classes outside of the package.
- Instead, it can be used this technique/architecture:

#### Package by Feature:
- Here it is a structure like this:
- packages: main
- || users
  ||  - controllers
  |  - models
  |  - services
  |
- || receipts
  ||  - controllers
  |  - models
  |  - services

####### Organized it in a way that each feature has their own Controller/Model/Service classes.


## Use of Beans
- @Bean -> instance of a class with metadata around it (manageable by Spring)


## Using "Record"

#### It can be created a Record instead of a class when an object’s only purpose is to contain public data and it will be immutable.
- Using Record allows to save all those lines of getters, setters, hashCode, etc.
- Its like having a AllArgsConstructor but we can`t set the fields


## Spring MVC 
- Model -> Run class
- View -> Rest API - JSON returned as data
- Controller -> Receive request, delegate to services and return a response (dont put logic here)

#### Repository Pattern
- Used this for data access

###### Singleton
RunRepository is suppose to have only one instance of it when running program - it should not be used new RunRepository()

### @PathVariable vs @PathParam
- PathParam is from JAX-RS so it can only be used on REST
- PathVariable is from Spring so it works on both REST and MVC


## Validations
Add dependency: spring-boot-starter-validation -> Allows to use: @NotEmpty, @NotNull, ....

- @Valid -> Spring will validate fields with annotations before call the method
and will throw a 400 Bad Request

## Use H2 as in-memory DB 
- schema.sql to create queries -> is only picked up by embedded dbs by default

###### Repository
Communicates with database:
- using jdbcClient.sql("xxx") -> query to return data from DB
- using jdbcClient.sql("xxx").query(Class) -> map result from DB


## Dependency Injection
- Spring knows that creating an instance of RunsJsonDataLoader it is dependent that 
an instance of RunRepository also exists, injecting it to the constructor

## Docker

### docker-compose.yml file
- image:latest -> might not be a good practice if all members are supposed to use the same version
- ports:'1111' -> on container side it will run on this port

## PostgresSQL DB
- Spring will create a JDBC connection and read from docker-compose file at runtime
to fill properties

## Spring Data 
- Allows to define an interface in Spring Data and turn it into an implementation at runtime
- It is not needed to write all the CRUD methods code (allows to bypass this)

### This project uses Spring Data JDBC (Class Run Repository)
-> Allows to write dynamic queries from repository`s method name -> Example: findByAttribute()

##### ListCrudRepository interface
- Already provides methods saveAll, findAll and findAllById
- And extends CrudRepository with all CRUD methods available

### Spring Data JPA (Java Persistence Abstraction)
- By default, it uses Hibernate -> ORM (Object–Relational Mapping) tool
---> provides a framework for mapping an object-oriented domain model to a relational database


## REST Clients

- A higher-level abstraction built on top of WebClient used to send HTTP requests to interact with REST APIs
- It allows to test, debug, and integrate APIs while handling responses like status codes and body content

### HTTP Interface
- It allows to communicate with another service by defining an interface 
- It is not needed to write the low-level code implementation

---> Dependency is already added with Spring Web


## Testing

- Spring Boot Starter Test -> its only loaded on tests module
-> This imports Spring Boot test modules and also JUnit

###### SpringBootTest

- It loads the entire application context which will make tests slower


### Slice Tests

-> This allows to only load the necessary things for the test

##### JdbcTest

- JDBC Tests are Transactional and roll back at the end of each test
