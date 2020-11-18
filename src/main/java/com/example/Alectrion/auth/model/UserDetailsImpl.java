package com.example.Alectrion.auth.model;

import com.example.Alectrion.model.Persona;
import com.example.Alectrion.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails{

    final private String username;
    final private String password;
    final private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl( Persona user ){
        this.username = user.getUsername( );
        this.password = user.getPassword( );
        this.authorities = translateRoles( user.getRoles( ) );
    }

    @Override
    public String getUsername( ){
        return username;
    }

    @Override
    public String getPassword( ){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities( ){
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired( ){
        return true;
    }

    @Override
    public boolean isAccountNonLocked( ){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired( ){
        return true;
    }

    @Override
    public boolean isEnabled( ){
        return true;
    }

    private Collection<? extends GrantedAuthority> translateRoles( List<Role> roles ){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>( );
        for( Role role : roles ){
            String roleName = "ROLE_" + role.getRoleName( ).toUpperCase( );
            grantedAuthorities.add( new SimpleGrantedAuthority( roleName ) );
        }
        return grantedAuthorities;
    }
}
