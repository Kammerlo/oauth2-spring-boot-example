package org.cardanofoundation.authtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.cardanofoundation.authtest.dto.TokenResponse;
import org.cardanofoundation.authtest.service.KeycloakService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KeycloakService keycloakService;

    @GetMapping("/token")
    @Operation(summary = "auth Endpoint", description = "auth endpoint")
    public ResponseEntity<TokenResponse> authEndPoint(@RequestParam String username, @RequestParam String password) {

        TokenResponse tokenResponse = keycloakService.getToken(username, password);

        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
