
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


 Authentication vs autorization
 - authentication is the process of recognizing who you are, and after it you grant access to some functionalities
 - autorization is the process of giving you the special rights based on authentication. It decides which resources are
 available for you