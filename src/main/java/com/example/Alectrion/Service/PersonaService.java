package com.example.Alectrion.Service;

import com.example.Alectrion.dao.api.EstablishmentDaoAPI;
import com.example.Alectrion.dao.api.FavoriteDaoAPI;
import com.example.Alectrion.dao.api.PersonaDaoAPI;
import com.example.Alectrion.dao.api.ReserveDaoAPI;
import com.example.Alectrion.model.*;
import com.example.Alectrion.pojo.RegisterUserPOJO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class PersonaService{

    private final PersonaDaoAPI userRepository;

    private final FavoriteDaoAPI favoriteDaoAPI;

    private final EstablishmentDaoAPI establishmentDaoAPI;

    private final ReserveDaoAPI reserveDaoAPI;

    public PersonaService( PersonaDaoAPI userRepository, FavoriteDaoAPI favoriteDaoAPI,
                           EstablishmentDaoAPI establishmentDaoAPI, ReserveDaoAPI reserveDaoAPI ){
        this.userRepository = userRepository;
        this.favoriteDaoAPI = favoriteDaoAPI;
        this.establishmentDaoAPI = establishmentDaoAPI;
        this.reserveDaoAPI = reserveDaoAPI;
    }

    public Persona findByUsername(String username ){
        return userRepository.findByUsername( username );
    }

    public Persona findById(int id){ return userRepository.findById(id);}



    public void save( Persona persona ){
        userRepository.save( persona );
    }

    public List<Establishment> findFavorites(int id){
        List<Favorites> favorites = favoriteDaoAPI.findByFavoritePK_Persona_Id(id);
        List<Establishment> temp = new ArrayList<>();
        for ( Favorites f : favorites
             ) {
            temp.add(establishmentDaoAPI.findById(f.getIdEstablishment()));
        }
        return temp;
    }

    public List<Reserve> findReserves(int user_id){ return reserveDaoAPI.findByReservePK_Persona_Id(user_id);}

    public void saveReserve(Reserve reserve){ reserveDaoAPI.save( reserve );}

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

    public void deleteFavorite(Favorites favorite){ this.favoriteDaoAPI.delete(favorite);}

    public void deleteReserve(Reserve reserve){ this.reserveDaoAPI.delete(reserve);}
    
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

        favoriteDaoAPI.deleteByFavoritePK_Persona_Id(user_id);
        reserveDaoAPI.deleteByReservePK_Persona_Id(user_id);
        userRepository.deleteById(user_id);
    }
}