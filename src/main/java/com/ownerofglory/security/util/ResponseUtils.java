package com.ownerofglory.security.util;

import com.ownerofglory.configuration.ApplicationProperties;
import org.springframework.http.HttpHeaders;

public class ResponseUtils {

    private ResponseUtils() {
    }

    public static HttpHeaders createHeaderWithJwt(ApplicationProperties.Auth auth, String jwt) {
        String header = auth.getAuthorizationHeader();
        String prefix = auth.getAuthorizationPrefix();
        String fullToken = prefix + " " + jwt;
        HttpHeaders headers = new HttpHeaders();
        headers.set(header, fullToken);
        return headers;
    }

}
