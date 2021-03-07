package com.west.auth.oauth2.converter;

import com.west.auth.oauth2.constant.OAuth2GiteeContants;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

/**
 * @author west
 * @date 2021/3/4 10:21
 */
public class GiteeAuthorizationCodeGrantRequestEntityConverter implements Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> {
    @Override
    public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        HttpHeaders headers = getTokenRequestHeaders();
        URI uri = buildUri(authorizationCodeGrantRequest);
        return new RequestEntity<>(headers, HttpMethod.GET, uri);
    }

    private HttpHeaders getTokenRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
        return headers;
    }

    private URI buildUri(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        ClientRegistration clientRegistration = authorizationCodeGrantRequest.getClientRegistration();
        String tokenUri = clientRegistration.getProviderDetails().getTokenUri();
        String clientId = clientRegistration.getClientId();
        String redirectUri = clientRegistration.getRedirectUri();
        String clientSecret = clientRegistration.getClientSecret();
        String code = authorizationCodeGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode();
        String grantType = authorizationCodeGrantRequest.getGrantType().getValue();

        String uriString = String.format(OAuth2GiteeContants.GITEE_AUTHORIZATION_REQUEST_URL_FORMAT, tokenUri, clientId, clientSecret, code, redirectUri, grantType);
        return UriComponentsBuilder.fromUriString(uriString).build().toUri();
    }
}
