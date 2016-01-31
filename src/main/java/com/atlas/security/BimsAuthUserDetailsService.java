package com.atlas.security;

import com.atlas.common.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 */
public class BimsAuthUserDetailsService implements AuthenticationUserDetailsService {

    private static final Log logger = LogFactory.getLog(BimsAuthUserDetailsService.class);

    private RestTemplate restTemplate = new RestTemplate();

    private String bimsValidateUrl;


    public BimsAuthUserDetailsService(String bimsValidateUrl) {
        this.bimsValidateUrl = bimsValidateUrl;
        assert bimsValidateUrl!=null;
    }

    @Override
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        HttpEntity<String> entity = Util.addTokenToHeader((String) token.getCredentials());

        ResponseEntity<com.atlas.security.types.UserDetails> response = restTemplate.exchange(bimsValidateUrl, HttpMethod.GET, entity, com.atlas.security.types.UserDetails.class, token);
        boolean notvalid = response.getBody().isHasError();
        if (notvalid)
            throw new UsernameNotFoundException("");
        return response.getBody();
    }


}
