package com.example.Alectrion.auth.service;

import com.example.Alectrion.Service.PersonaService;
import com.example.Alectrion.auth.model.UserDetailsImpl;
import com.example.Alectrion.model.Persona;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service( "userDetailsService" )
public class UserDetailsServiceImpl implements UserDetailsService {

    private PersonaService userService;

    public UserDetailsServiceImpl(PersonaService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Persona user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return new UserDetailsImpl(user);
    }
}
