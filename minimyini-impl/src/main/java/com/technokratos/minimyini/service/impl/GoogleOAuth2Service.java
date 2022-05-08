package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.GoogleTokenDto;
import com.technokratos.minimyini.dto.TokenDto;
import com.technokratos.minimyini.service.AuthenticationService;
import com.technokratos.minimyini.service.OAuth2Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleOAuth2Service implements OAuth2Service {

    private final RestTemplate restTemplate;
    private final AuthenticationService authenticationService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-url}")
    private String redirectUrl;

    @Value("${spring.security.oauth2.client.registration.google.grant-type}")
    private String grantType;

    @Value("${spring.security.oauth2.client.registration.google.code-exchange-url}")
    private String codeExchangeUrl;

    @Override
    public TokenDto signIn(String code) {
        GoogleTokenDto response = getAuthorizationTokens(code);
        log.info(response.getAccessToken());
        String idToken = response.getIdToken();
        String withoutSignature = idToken.substring(0, idToken.lastIndexOf('.') + 1);
        Claims claims = Jwts.parser().parseClaimsJwt(withoutSignature).getBody();

        String email = claims.get("email", String.class);
        String firstName = claims.get("given_name", String.class);
        String lastName = claims.get("family_name", String.class);

        return authenticationService.googleSignIn(email, firstName, lastName);
    }

    private GoogleTokenDto getAuthorizationTokens(String code) {
        Map<String, String> params = new HashMap<>();
        String url = getUrl(code, params);
        HttpEntity<?> entity = new HttpEntity<>(null);
        return restTemplate.postForEntity(url, entity, GoogleTokenDto.class, params).getBody();
    }

    private String getUrl(String code, Map<String, String> params) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(codeExchangeUrl)
                .queryParam("code", "{code}")
                .queryParam("client_id", "{client_id}")
                .queryParam("client_secret", "{client_secret}")
                .queryParam("redirect_uri", "{redirect_uri}")
                .queryParam("grant_type", "{grant_type}")
                .encode()
                .toUriString();

        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUrl);
        params.put("grant_type", grantType);

        return urlTemplate;
    }
}
