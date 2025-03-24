package com.se.Online.Food.Ordering.System.model;//package com.se.Online.Food.Ordering.System.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.List;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final String username;
//    private final String password;
//    private final List<GrantedAuthority> authorities;
//
//    public CustomUserDetails(User user) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.authorities = List.of(() -> user.getRole());  // Mapping the role to authorities
//    }
//
//    @Override
//    public List<GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();

        // Correctly mapping role to GrantedAuthority
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
