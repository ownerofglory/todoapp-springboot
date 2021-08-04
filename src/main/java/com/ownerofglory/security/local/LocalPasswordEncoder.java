package com.ownerofglory.security.local;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LocalPasswordEncoder extends BCryptPasswordEncoder {

    public LocalPasswordEncoder() {
        super(5);
    }
}
