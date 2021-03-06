package com.razvan.TestCenterCovid.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private User user;


    private String userName;
    private String password;
    private String name;
    private long id;


    public void setId(long id) {
        this.id = id;
    }

    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(com.razvan.TestCenterCovid.models.User user) {
        this.user = user;
        this.userName = user.getCPR();
        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public long getId()
    {return this.user.getId();}

    public String getName()
    {
        return this.user.getName();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
