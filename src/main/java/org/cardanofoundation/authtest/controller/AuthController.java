package org.cardanofoundation.authtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.cardanofoundation.authtest.dto.TokenResponse;
import org.cardanofoundation.authtest.dto.UsernamePasswordDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${keycloak.token-url}")
    private String tokenUrl;

    @Value("${keycloak.resource}")
    private String clientId;

    @GetMapping("/token")
    @Operation(summary = "auth Endpoint", description = "auth endpoint")
    public ResponseEntity<TokenResponse> authEndPoint(@RequestParam String username, @RequestParam String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        WebClient webClient = WebClient.create();
        TokenResponse tokenResponse = webClient.post()
                .uri(tokenUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=password" +
                        "&client_id=" + clientId +
                        "&username=" + username +
                        "&password=" + password)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .onErrorReturn(new TokenResponse())
                .block();
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
