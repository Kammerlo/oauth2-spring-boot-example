# OAuth 2 Spring Boot Example
This is an example Repository to demonstrate how to implement OAuth 2.0 in a Spring Boot application.
It is offering a simple controller with two endpoints:
- `/admin` - only accessible for users with the role `ADMIN`
- `/user` - accessible for for users with the role `user`
- `/no-role` - Accessible for all users without a specific role
- `/public` - public endpoint

### Requirements
- Java 21
- Keycloak running an accessible

### Configuration
- Add a realm in Keycloak
- Add a client in Keycloak
- Add a Realm Roles in Keycloak (`admin`, `user`)
- Add a user in Keycloak and assign the roles `admin` and `user` to the user
- Adjust the configuration in `src/main/resources/application.properties` to match your Keycloak configuration

