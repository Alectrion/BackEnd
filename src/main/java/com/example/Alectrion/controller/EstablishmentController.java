package com.example.Alectrion.controller;

import com.example.Alectrion.Service.EstablishmentService;
import com.example.Alectrion.Service.PersonaService;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.model.Persona;
import com.example.Alectrion.pojo.NumberReservesPOJO;
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

        Establishment existingEstablishment = establishmentService.findByEstName(estPojo.getEstName());
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        Persona existingUser = personaService.findByUsername( username );

        if( existingEstablishment != null || !establishmentService.isRightEstablishment( estPojo ) ){
        	return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

        Establishment newEstablishment = new Establishment( );
        newEstablishment.setEstName( estPojo.getEstName() );
        newEstablishment.setDir( estPojo.getDir() );
        newEstablishment.setTel( estPojo.getTel().toLowerCase( ) );
        newEstablishment.setId_propietario(existingUser);
        newEstablishment.setTipoEstablecimiento(estPojo.getTipoEstablecimiento());
        newEstablishment.setCupoMax(estPojo.getCupoMax());
        newEstablishment.setMuro(estPojo.getMuro());
        newEstablishment.setLatitud(estPojo.getLatitud());
        newEstablishment.setLongitud(estPojo.getLongitud());
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

    @PostMapping( value = { "/establecimientos/ocupacion"})
    public int getOcupationEstablishment(@RequestBody NumberReservesPOJO numberReservesPOJO){

        return establishmentService.getScheduleCapacity(numberReservesPOJO.getEstID(), numberReservesPOJO.getHorario());
    }




    @PutMapping( value = { "/propietario/establecimiento/editar/{Id}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> updateEstablishment(@PathVariable Integer Id,@RequestBody RegistrerEstablishmentPOJO estPojo  ){
    	Establishment existingEstablishment = establishmentService.findByEstId(Id);
    	
    	existingEstablishment.setEstName( estPojo.getEstName());
    	existingEstablishment.setDir( estPojo.getDir());
    	existingEstablishment.setTel( estPojo.getTel());
    	existingEstablishment.setTipoEstablecimiento(estPojo.getTipoEstablecimiento());
    	existingEstablishment.setCupoMax(estPojo.getCupoMax());
    	existingEstablishment.setMuro(estPojo.getMuro());
    	existingEstablishment.setLatitud(estPojo.getLatitud());
        existingEstablishment.setLongitud(estPojo.getLongitud());
    	establishmentService.save(existingEstablishment);
    	return new ResponseEntity<>( HttpStatus.OK );
	}
    
}