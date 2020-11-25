package com.example.Alectrion.auth.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

    private static final String[] publicResources = new String[]{ "/registro/nuevo-usuario/rol/**", "/roles", "/publico" };
    private static final String[] userResources = new String[]{ "/usuario/**", "/registro/nuevo-rol/**" };
    private static final String[] ownerResources = new String[]{ "/propietario/**" };
    private static final String[] clientResources = new String[]{ "/cliente/**" };

    @Override
    public void configure( HttpSecurity httpSecurity ) throws Exception{
        httpSecurity
                .authorizeRequests( )
                .antMatchers( publicResources ).permitAll( )
                .antMatchers( userResources ).authenticated( )
                .antMatchers( ownerResources ).hasAuthority( "ROLE_PROPIETARIO" )
                .antMatchers( clientResources ).hasAuthority( "ROLE_USUARIO" )
                .and( ).cors( ).disable( );


    }


}