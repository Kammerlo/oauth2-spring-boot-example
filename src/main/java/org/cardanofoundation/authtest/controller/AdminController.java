package org.cardanofoundation.authtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example of a controller that only users with the role 'admin' can access
 * You don't have to set the preAuthorization for every endpoint, you can also set it on the class level
 */
@RestController
@PreAuthorize("hasRole('admin')")
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/admin")
    @Operation(summary = "admin Endpoint", description = "admin endpoint")
    public ResponseEntity<String> adminEndPoint() {
        return ResponseEntity.ok("You have the role Admin!");
    }
}
