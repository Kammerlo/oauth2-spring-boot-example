package org.cardanofoundation.authtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    @Operation(summary = "admin Endpoint", description = "admin endpoint")
    @ApiResponse(responseCode = "200", description = "Only accessible for users with the role Admin")
    public ResponseEntity<String> adminEndPoint() {
        return ResponseEntity.ok("You have the role Admin!");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('user')")
    @Operation(summary = "User Endpoint", description = "User endpoint")
    @ApiResponse(responseCode = "200", description = "Only accessible for users with the role User")
    public ResponseEntity<String> userEndPoint() {
        return ResponseEntity.ok("You have the role User!");
    }

    @GetMapping("/no-role")
    @PreAuthorize("permitAll()")
    @Operation(summary = "No Role Endpoint", description = "No role3 endpoint")
    @ApiResponse(responseCode = "200", description = "Accessible for all registered users without a role")
    public ResponseEntity<String> allEndPoint() {
        return ResponseEntity.ok("You can access this endpoint without a specific role!");
    }

    // Public Endpoints must be added to the SecurityConfig.java file
    @GetMapping("/public")
    @Operation(summary = "Public Endpoint", description = "Public endpoint")
    @ApiResponse(responseCode = "200", description = "Accessible for all users")
    public ResponseEntity<String> publicEndPoint() {
        return ResponseEntity.ok("Everyone can access this endpoint!");
    }
}
