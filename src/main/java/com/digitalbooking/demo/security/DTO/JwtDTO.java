package com.digitalbooking.demo.security.DTO;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class JwtDTO {
    private String token;
    private String bearer = "Bearer";
    private Long id;
    private String name;
    private String surname;
    public String email;
    private Collection< ? extends GrantedAuthority> authority;

    public JwtDTO(Long id, String name, String surname, String token, String email, Collection<? extends GrantedAuthority> authority) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.token = token;
        this.email = email;
        this.authority = authority;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthority() {
        return authority;
    }

    public void setAuthority(Collection<? extends GrantedAuthority> authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
