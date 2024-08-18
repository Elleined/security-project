# security-project
A project that covers custom configuration, role-based authorization, social login, and jwt token

# Authentication
- via Sign up and Login (Username and password)
- via OAuth (Social Login)
## Authentication factors
- Single factor: Only requires username and password.
- 2 Factor Authentication (2FA): Requires username and password, security question, and OTP.

## Authentication Techniques
- Password-based
- OTP
- Pin
- Security questions
- Social Authentication (OAuth)

# Authorization
- Controls what resources does authenticated users can access. We dont want a regular users doing what admins can do right?.
- Works after the user is authenticated via different authentication techniques. It check if the user have access/ permission to the specific operation.
- hasAnyRole() method
- hasRole() method

## Authorization Techniques
- Role-based
- JWT(JSON Web Token)
- OAuth/ OpenID

# Claims
- Works after authorization of the user via different authorization techniques, a fine grained permission it ensures that users with the same role have the right(claim) to do specific operation, thus providing granular control over what user can access.
- Does this authorized user has the right/ claim to do this specific operation because claim are given to the user by the application.
- hasAnyAuthority() method
- hasAuthority() method

## Analogy
- Just like when you have siblings(users), and your parents(application) will give properties(operations) with each one of their daughter/ son(users). Given that you are all their daughter/ son(users) meaning that your siblings doesn't automatically have a right(claim) in your property(operation).

- Just like in family settings you have siblings(users) and you are all living in the same house because you are all daughters/ sons of your parents(application) meaning that you are all authorize to enter the house. But think of claims each one of you has their own room(operation) in the house right? Your siblings does not have automatic right(claim) to enter your room(operation). Because claims is working after a succesaful authorization.

# Workflow
1. Authentication: you will provide username and password and the app will authenticate your credential.
2. Authorization: comes after you have been authenticated not all the users have the authorization to just use all the operations in your app right?. (Role based)
3. Claims: comes after you have been authorized, because not all users with the same role have a right to do specific operations right?.

## Analogy
1. Authentication: You go to the club the bouncers(login) will check you id card(username and password).
2. Authorization: Now you are inside the club. Inside the club there's staff and customers(roles) you don't want the staff drinking alchohol right? and customers are just casually going inside and out in the admin office right?.
3. Claims: Now that you are inside the club, suppose you are the staff, and staff we all know theres an heirarchy pf position the staff boss and regular staff and we dont want the regular staff just casually signing an important contract right?. which only the staff boss are allowed to do.

# Spring Security
Web Flow
1. When user send a request it goes to controller but how exactly spring know what to do with the request?

2. First thing we should know is that all the controllers will be converted into servlets by spring so basically controllers are like wrappers for servlets.

3.But before a request goes to a servlet theres a main servlet that will be invoked first that servlet is called front controller also known as DispatcherServlet from the name itself it dispatches the requests to different servlet accordingly.

4. And before the DispatcherServlet is invoked theres another layer of protection called FilterChain basically it filters the request before going to the DispatcherServlet doing a lot validation to secure the request before invoking the DispatcherServlet.

So in conclusion:
- The user sends a request and it will first go to a series of FilterChain then DispatcerServlet then the actual Servlet.

# So how does spring security works?
- By default spring security will have username and password generated and have a default login form and will secure all the endpoint.

- First we create a configuration class that will have a SecurityFilterChain bean basically we are saying that hey spring security don't go to your default filter chain instead go here what I mention in this method. So in this method we will tell spring security how he should handle the security of our app by specifying it.

# What is the important config we should add?
1. Set session management always to STATELESS what it means is that every request is a new resource and for every resource you need to login right? thus Avoiding CSRF by setting this. And also setting this will enforce you to implement JWT Token.

2. Disable CSRF because if you do the step 1 you know why. There no other way just do what I tell saving you time and money researching why.

# Methods
.authorizeHttpRequests
- Will let you customize what endpoints should be secured and not secured.
    - hasRole
    - hasAnyRole

.formLogin
- Will let you customize the default login module including the UI and Backend of the login module.

.oauth2Login
- Will let you customize the OAuth login that will let your user sign in with their social accounts.

.httpBasic
- Will let you customize how the use will be authenticated like allowing them to login in POSTMAN because in formLogin you only allow them to login in the web browser.

# Important interfaces you need to understand
1. SecurityFilterChain
- Remember that every request goes through a series of filter right? Basically we are just adding our specific filter to the filter chain of spring by defining a bean of this class.

2. AutheticationProvider/ AuthenticationManager
- Will acts as a repository layer in spring security. This will handle the database connection fetching all the user in your database.

3. UserDetails and UserDetailsService
- UserDetails will act as the model layer for the spring security to determine the details of the user from the name itself.

- UserDetailsService will act as the service layer for the spring security to determine a specific UserDetails object based on the given username in the method loadUsername.

4. PasswordEncoder
- Will handle how the password will be encrypted and decoded.

5. GrantedAuthority
- Basically the authorization which is the ROLE of specific UserDetails.

6. Principal
- Refers to the current user of the system.

# JWT
- Stands for JSON Web Token.
- Is just a wrapper for JSON with extra security features.

# Three parts of JWT
- Header: This part identifies the type of encryption and other data needed to decode the token

- Payload: The data you've encoded into the token like the username, maybe a user role, or a boolean that they are logged in (whatever you want).

- Signature: This is the unique signature that is paired with a Secret Key. If the same key isn't used to decode the string, it won't decode correctly. (The application uses the same secret key for all tokens, the user never knows this exists)

# JWT Workflow
![SAVE_20240713_215346](https://github.com/user-attachments/assets/3146a391-bd24-4e28-af99-ca47a3d7a106)

1. When the user calls the api the first thing that gets executed is the JWTAuthenticationFilter that checks the jwt token that the user has.
2. After the JWTAuthenticationFilter checks and extract the email from the request, if the jwt is present it will call the UserDetailsService to fetch the user details in the database, otherwise will return NoExistingUser.
3. And if the user is exisiting, the next step is, it will call the JWTService that takes two parameters User and jwt token to validate the jwt because one user is one jwt it will checks if the given jwt is match to user fetch jwt. If it matches it will proceed otherwise it will return InvalidJWTToken.
4. Next is spring updates the set user in SecurityContextHolder that the specific user is now authenticated, because earlier we set the user when we call the UserDetailsService that are not yet authenticated so that we need to update to tell spring after so much validation the user is now validated.
5. After bloody validation the DispatcherServlet will now be called and the user request will get executed and function as normal like it intended it to do through Controllers, controllers to service, and so on...


# Useful Links
https://medium.com/@tericcabrel/implement-jwt
-authentication-in-a-spring-boot-3-application-5839e4fd8fac

https://m.youtube.com/watch?v=BVdQ3iuovg0

https://github.com/ali-bouali/spring-boot-3-jwt-security

https://www.javatpoint.com/authentication-vs-authorization

https://github.com/phegondev/users-management-system/blob/java-angular/backend/src/main/java/com/phegondev/usersmanagementsystem/config/JWTAuthFilter.java

