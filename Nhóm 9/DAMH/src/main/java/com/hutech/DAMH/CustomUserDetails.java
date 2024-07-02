package com.hutech.DAMH;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.lang.String;
import java.util.Collection;
public class CustomUserDetails extends User {
    private final int userId;

    public CustomUserDetails(String username, String password, int userId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;


    }

    public int getUserId() {
        return userId;
    }
    





}

