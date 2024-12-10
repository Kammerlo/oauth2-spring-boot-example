package org.cardanofoundation.authtest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cardanofoundation.authtest.dto.TokenResponse;
import org.cardanofoundation.authtest.service.KeycloakService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final KeycloakService keycloakService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Add a refreshed token to the response headers
        String refreshToken = request.getHeader("refresh_token");

        if (refreshToken != null && response.isCommitted()) {
            TokenResponse tokenResponse = keycloakService.refreshToken(refreshToken);
            response.addHeader("access_token", tokenResponse.getAccess_token());
            response.addHeader("refresh_token", tokenResponse.getRefresh_token());
            response.addHeader("expires_in", tokenResponse.getExpires_in());
        }
    }
}
