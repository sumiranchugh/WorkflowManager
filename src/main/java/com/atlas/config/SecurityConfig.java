package com.atlas.config;

import com.atlas.security.BimsAuthUserDetailsService;
import com.atlas.security.BimsPreAuthenticatedProcessingFilter;
import com.atlas.security.HttpEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.List;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${bims.url}")
    private String bimsUrl;

    @Value("${client.id}") // registeredUrl
    private String clientId;

    @Value("{bims.validatetoken}")
    private String bimsValidateUrl;


    @Override
    public void configure(HttpSecurity http) throws Exception {
http.authorizeRequests().anyRequest().authenticated().and().addFilter(abstractPreAuthenticatedProcessingFilter
        (authenticationManager())).exceptionHandling().authenticationEntryPoint(getAuthEntryPoint());
    }


    @Bean
    public Filter abstractPreAuthenticatedProcessingFilter(AuthenticationManager manager){
        AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter = new BimsPreAuthenticatedProcessingFilter();
        preAuthenticatedProcessingFilter.setAuthenticationManager(manager);
        return preAuthenticatedProcessingFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        List<AuthenticationProvider> providerList = new ArrayList<>();
        providerList.add(authenticationProvider());
        return new ProviderManager(providerList);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
        return provider;
    }


    @Bean
    public AuthenticationUserDetailsService authenticationUserDetailsService(){
        return new BimsAuthUserDetailsService(bimsValidateUrl);
    }


    public AuthenticationEntryPoint getAuthEntryPoint() {
        return new HttpEntryPoint(bimsUrl,clientId);
    }
}
