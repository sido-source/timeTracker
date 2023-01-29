
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


-----------------------------------------------------------------------------------------------------------
 Start developing login/register feature, this section is based on: https://www.bezkoder.com/spring-boot-jwt-authentication/

 - at the beginning I added 2 entities: User and Role and specify ERole, where I keep all of the rules
 - next I created repositories to be able to do some database operations

Configure Spring Security
is the crux of our security implementation. It configures cors, csrf, session management, rules for protected resources.
We can also extend and customize the default configuration that contains the elements below.

1. security -> WebSecurityConfig
– @EnableWebSecurity allows Spring to find and automatically apply the class to the global Web Security.

– @EnableGlobalMethodSecurity provides AOP security on methods. It enables @PreAuthorize, @PostAuthorize, it also supports JSR-250. You can find more parameters in configuration in Method Security Expressions.

– We override the configure(HttpSecurity http) method from WebSecurityConfigurerAdapter interface. It tells Spring Security how we configure CORS and CSRF, when we want to require all users to be authenticated or not, which filter (AuthTokenFilter) and when we want it to work (filter before UsernamePasswordAuthenticationFilter), which Exception Handler is chosen (AuthEntryPointJwt).

– Spring Security will load User details to perform authentication & authorization. So it has UserDetailsService interface that we need to implement.

– The implementation of UserDetailsService will be used for configuring DaoAuthenticationProvider by AuthenticationManagerBuilder.userDetailsService() method.

– We also need a PasswordEncoder for the DaoAuthenticationProvider. If we don’t specify, it will use plain text.



2. IMPLEMENT UserDetails & UserDetailsService
Look at the code above, you can notice that we convert Set<Role> into List<GrantedAuthority>. It is important to work with Spring Security and Authentication object later.

We need UserDetailsService for getting UserDetails object.

we get full custom User object using UserRepository, then we build a UserDetails object using static build() method.


3. Filter the Requests
define a filter that executes once per request. So we create AuthTokenFilter class that extends OncePerRequestFilter and override doFilterInternal() method

What we do inside doFilterInternal():
– get JWT from the Authorization header (by removing Bearer prefix)
– if the request has JWT, validate it, parse username from it
– from username, get UserDetails to create an Authentication object
– set the current UserDetails in SecurityContext using setAuthentication(authentication) method.

After this, everytime you want to get UserDetails, just use SecurityContext like this:

UserDetails userDetails =
	(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

4.Handle Authentication Exception
  Now we create AuthEntryPointJwt class that implements AuthenticationEntryPoint interface. Then we override the commence() method. This method will be triggerd
  anytime unauthenticated User requests a secured HTTP resource and an AuthenticationException is thrown.




