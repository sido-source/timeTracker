
Functionalities:
 - adding
 - deleting
 - updating
 - read


 Regarding Company entity:
 There was used the dto, which deletes/not show id the customer/client.
 As the unique value, was set name field (there is registered only one company with the same name)


 Usefull links to use in the feature:
 https://www.toptal.com/java/spring-boot-rest-api-error-handling


 Repositories are used to communicate with database. If we want to do something on database we need to implement
 already defined operations, for example CrudRepository or create our own
 All repositories function can be used in Service

 Service is the layer between controller which uses Dto model and database and entity model
 In Service interface we define only business methods
 One of this method can implements many methods from repository for example in update function we need to
 - check if the id is valid and unique (1 method from repository)
 - save object (2 method from repository)

 Service interfaces define business methods used by controller, but repositories define methods used in service



 Start developing login/register feature:
 - at the beginning I added 2 entities: User and Role and specify ERole, where I keep all of the rules
 - next I created repositories to be able to do some database operations
