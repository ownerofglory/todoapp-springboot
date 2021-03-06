package com.ownerofglory.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private final Auth auth = new Auth();

    @Getter
    @Setter
    public static class Auth {
        private String authorizationHeader;
        private String authorizationPrefix;
        private String tokenSecret;
        private long tokenExpirationSec;
    }
}
