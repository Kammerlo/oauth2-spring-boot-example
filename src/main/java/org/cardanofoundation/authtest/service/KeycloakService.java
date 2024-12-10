package org.cardanofoundation.authtest.service;

import org.cardanofoundation.authtest.dto.TokenResponse;

public interface KeycloakService {

    TokenResponse getToken(String username, String password);

    TokenResponse refreshToken(String refreshToken);

}
