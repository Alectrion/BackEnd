package com.example.Alectrion.Service;

import com.example.Alectrion.dao.api.EstablishmentDaoAPI;
import com.example.Alectrion.dao.api.FavoriteDaoAPI;
import com.example.Alectrion.dao.api.PersonaDaoAPI;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.model.Favorites;
import com.example.Alectrion.model.Persona;
import com.example.Alectrion.model.Role;
import com.example.Alectrion.pojo.RegisterUserPOJO;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class PersonaService{

    private final PersonaDaoAPI userRepository;

    private final FavoriteDaoAPI favoriteDaoAPI;



    public PersonaService( PersonaDaoAPI userRepository, FavoriteDaoAPI favoriteDaoAPI, EstablishmentDaoAPI establishmentDaoAPI ){
        this.userRepository = userRepository;
        this.favoriteDaoAPI = favoriteDaoAPI;

    }

    public Persona findByUsername(String username ){
        return userRepository.findByUsername( username );
    }

    public Persona findById(int id){ return userRepository.findById(id);}



    public void save( Persona persona ){
        userRepository.save( persona );
    }

    public void saveFavorite( Favorites favorite){ this.favoriteDaoAPI.save( favorite ); }

    public boolean isRightUser( RegisterUserPOJO user ){
        boolean correctness = user.getNames( ) != null && user.getPassword( ) != null && user.getUsername( ) != null &&
                user.getEmail( ) != null;

        if( correctness ){
            correctness = !user.getNames( ).trim( ).isEmpty( )
                    && !user.getPassword( ).trim( ).isEmpty( )
                    && !user.getUsername( ).trim( ).isEmpty( )
                    && !user.getEmail( ).trim( ).isEmpty( );
        }
        return correctness;
    }
    
    public Persona getPersona( ){
    	String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
    	return userRepository.findByUsername(username);
    } 
    
    
    public List<Persona> getAll(){
    	return userRepository.findAll();
    }
    
    public List<Role> getAllRole(){
    	String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
    	Persona persona = userRepository.findByUsername(username);
    	return persona.getRoles();
    }

    public List<Establishment> getEstablishments(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Persona persona = userRepository.findByUsername(username);
        return persona.getEstablishments();
    }
    
    
    public void deleteById(Integer user_id) {
        userRepository.deleteById(user_id);
    }
}