# OAuth 2 Spring Boot Example
This is an example Repository to demonstrate how to implement OAuth 2.0 in a Spring Boot application.
It is offering simple controllers to test the concept of authentication with specific roles.

Current Endpoints:
- `/finegrained-auth/[admin,user, no-role & public]` - Controller which demonstrates the finegrained authorization by setting the `preAuthorization` Annotation for each Endpoint
- `/admin/admin` - Controller which sets the `preAuthorization` on Class level, all endpoints will be secured by the `admin` role
- `/user/user` - Controller which sets the `preAuthorization` on Class level, all endpoints will be secured by the `user` role
- 
### Requirements
- Java 21
- Keycloak running an accessible

### Configuration
- Add a realm in Keycloak
- Add a client in Keycloak
- Add a Realm Roles in Keycloak (`admin`, `user`)
- Add a user in Keycloak and assign the roles `admin` and `user` to the user
- Adjust the configuration in `src/main/resources/application.properties` to match your Keycloak configuration

