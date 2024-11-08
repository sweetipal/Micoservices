package com.sweeti.gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweeti.gateway.models.AuthResponse;
//This is 
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client, OidcUser user) {
        logger.info("User email id: {}", user.getEmail());
        // Creating AuthResponse object
        AuthResponse authResponse = new AuthResponse();
        // Setting email to authResponse
        authResponse.setUserId(user.getEmail());
        // Setting Access Token to authResponse
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());
        // Setting Refresh Token to authResponse, if available
        if (client.getRefreshToken() != null) {
            authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        }
        // Setting Expiry Time to authResponse
        authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
        // Mapping and setting authorities
        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> { return grantedAuthority.getAuthority();}).collect(Collectors.toList());
        authResponse.setAuthorites(authorities);
        // Returning the response with OK status
        return ResponseEntity.ok(authResponse);
    }
}
