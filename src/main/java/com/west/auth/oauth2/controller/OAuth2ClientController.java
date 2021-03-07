package com.west.auth.oauth2.controller;

import com.west.auth.oauth2.context.OAuth2Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author west
 * @date 2021/3/4 10:33
 */
@Controller
public class OAuth2ClientController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/")
    public String index() {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(OAuth2Context.getClientRegistrationId(), OAuth2Context.getPrincipalName());
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();


        return "index";
    }
}
