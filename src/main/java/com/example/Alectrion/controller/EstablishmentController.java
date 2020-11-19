package com.example.Alectrion.controller;

import com.example.Alectrion.Service.EstablishmentService;
import com.example.Alectrion.Service.PersonaService;
import com.example.Alectrion.dao.api.EstablishmentDaoAPI;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.model.Persona;
import com.example.Alectrion.model.Role;
import com.example.Alectrion.pojo.LoginUserPOJO;
import com.example.Alectrion.pojo.RegisterUserPOJO;
import com.example.Alectrion.pojo.RegistrerEstablishmentPOJO;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstablishmentController {

    private EstablishmentService establishmentService;

    private PersonaService personaService;

    public EstablishmentController(EstablishmentService establishmentService, PersonaService personaService) {
        this.personaService = personaService;
        this.establishmentService = establishmentService;
    }

    @PostMapping( value = { "/propietario/nuevo_establecimiento" } )
    public ResponseEntity<Void> registerNewEstablishment(@RequestBody RegistrerEstablishmentPOJO estPojo ){
        System.out.println(estPojo.getNombreEstablecimiento());
        System.out.println(estPojo.getDir());
        System.out.println(estPojo.getTel());
        System.out.println(estPojo.getCupoMax());


        Establishment existingEstablishment = establishmentService.findByEstName(estPojo.getNombreEstablecimiento());
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        System.out.println(username);
        Persona existingUser = personaService.findByUsername( username );
        System.out.println(existingUser.getId());


        if( existingEstablishment != null || !establishmentService.isRightEstablishment( estPojo ) ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

        Establishment newEstablishment = new Establishment( );
        newEstablishment.setEstName( estPojo.getNombreEstablecimiento().toUpperCase( ) );
        newEstablishment.setDir( estPojo.getDir().toUpperCase() );
        newEstablishment.setTel( estPojo.getTel().toLowerCase( ) );
        newEstablishment.setId_propietario(existingUser.getId());
        newEstablishment.setTipoEstablecimiento(estPojo.getTipoEstablecimiento());
        newEstablishment.setCupoMax(estPojo.getCupoMax());
        establishmentService.save( newEstablishment );


        return new ResponseEntity<>( HttpStatus.CREATED );
    }
    
    @DeleteMapping( value = { "/propietario/establecimiento/{esta_Id}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> deleteEstablishment( @PathVariable Integer esta_Id ){
		establishmentService.deleteById(esta_Id);
		return ResponseEntity.ok(null);
    }
    
    @GetMapping( value = { "/Establecimientos" } )
    public List<Establishment> getAll( ){ return establishmentService.getAllEstablishments();
    }
    
    @PutMapping( value = { "/establecimiento/editar/{Id}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> updateEstablishment(@PathVariable Integer Id,@RequestBody RegistrerEstablishmentPOJO estPojo  ){
    	Establishment existingEstablishment = establishmentService.findByEstName(estPojo.getNombreEstablecimiento());
    	
    	existingEstablishment.setEstName( estPojo.getNombreEstablecimiento());
    	existingEstablishment.setDir( estPojo.getDir());
    	existingEstablishment.setTel( estPojo.getTel());
    	existingEstablishment.setTipoEstablecimiento(estPojo.getTipoEstablecimiento());
    	existingEstablishment.setCupoMax(estPojo.getCupoMax());
    	establishmentService.save(existingEstablishment);
    	return new ResponseEntity<>( HttpStatus.OK );
	}
    
}
