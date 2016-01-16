package com.atlas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by Sumiran Chugh on 1/10/2016.
 */
//@EnableOAuth2Client
public class OauthClientManager {


    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails(){
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setAccessTokenUri("https://182.71.216.178:13254/bimswebsite/Common/WebPages/Login.aspx");
        clientCredentialsResourceDetails.setClientId("Continue=https://182.71.216.178:13254/MDA-1/register");
           return clientCredentialsResourceDetails;
    }
}
