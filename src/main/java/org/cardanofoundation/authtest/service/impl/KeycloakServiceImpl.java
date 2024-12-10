package org.cardanofoundation.authtest.service.impl;

import org.cardanofoundation.authtest.dto.TokenResponse;
import org.cardanofoundation.authtest.service.KeycloakService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.token-url}")
    private String tokenUrl;

    @Value("${keycloak.resource}")
    private String clientId;


    @Override
    public TokenResponse getToken(String username, String password) {
        WebClient webClient = WebClient.create();
        return webClient.post()
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
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        WebClient webClient = WebClient.create();
        return webClient.post()
                .uri(tokenUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=refresh_token" +
                        "&client_id=" + clientId +
                        "&refresh_token=" + refreshToken)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .onErrorReturn(new TokenResponse())
                .block();
    }
}
