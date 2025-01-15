# Concepts Learning

## Use of Beans
- @Bean -> instance of a class with metadata around it (manageable by Spring)


## Structure
- It can be used something like:
- packages: main 
   |
   |  - controllers
   |  - models
   |  - services

- But controllers will need to communicate with classes outside of the package.
- Instead it can be used this technique/architecture:

#### Package by Feature:
- Here its a structure like this:
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


## Using "Record"

#### It can be created a Record instead of a class when an object’s only purpose is to contain public data and it will be immutable.
- Using Record allows to save all those lines of getters, setters, hashCode, etc.
- Its like having a AllArgsConstructor but we can`t set the fields


## Spring MVC 
- Model -> Run class
- View -> Rest API - JSON returned as data
- Controller -> Receive request, delegate to services and return a response (dont put logic here)

#### Repositort Pattern
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

## Use H2 as DB

- schema.sql to create queries
- data.sql to insert data 

###### Repository
Communicates with database:
- using jdbcClient.sql("xxx") -> query to return data from DB
- using jdbcClient.sql("xxx").query(Class) -> map result from DB


