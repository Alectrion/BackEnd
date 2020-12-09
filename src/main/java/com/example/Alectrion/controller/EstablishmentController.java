package com.example.Alectrion.controller;

import com.example.Alectrion.Service.EstablishmentService;
import com.example.Alectrion.Service.PersonaService;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.model.Persona;
import com.example.Alectrion.pojo.AforoPOJO;
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

import javax.transaction.Transactional;

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
        establishmentService.save( newEstablishment );

        return new ResponseEntity<>( HttpStatus.CREATED );
    }
    @Transactional
    @DeleteMapping( value = { "/propietario/establecimiento/{esta_Id}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> deleteEstablishment( @PathVariable Integer esta_Id ){
		establishmentService.deleteById(esta_Id);
		return ResponseEntity.ok(null);
    }

    @GetMapping(  value = { "/establecimiento/oaforo/{esta_Id}" } )
            public Integer getAforo(@PathVariable Integer esta_Id){
                Establishment establishment = establishmentService.findByEstId(esta_Id);
                return establishment.getAforo();
            }
    
    @GetMapping( value = { "/Establecimientos" } )
    public List<Establishment> getAll( ){ return establishmentService.getAllEstablishments();
    }

    @GetMapping(  value = { "/establecimiento/gaforo/{esta_Id}" } )
    public Integer[] getGrafica(@PathVariable Integer esta_Id){

        return establishmentService.getHorariosGrafica(esta_Id);
    }


    @PostMapping( value = { "/establecimientos/ocupacion"})
    public int getOcupationEstablishment(@RequestBody NumberReservesPOJO numberReservesPOJO){

        return establishmentService.getScheduleCapacity(numberReservesPOJO.getEstID(), numberReservesPOJO.getHorario());
    }

    @PutMapping( value = { "/establecimiento/eaforo"})
    public ResponseEntity<Void> updateEstablishment(@RequestBody AforoPOJO aforoPOJO){

        Establishment existingEstablishment = establishmentService.findByEstId(aforoPOJO.getEstID());
        existingEstablishment.setAforo(aforoPOJO.getAforo());
        establishmentService.save(existingEstablishment);
        return new ResponseEntity<>( HttpStatus.OK );
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
    	establishmentService.save(existingEstablishment);
    	return new ResponseEntity<>( HttpStatus.OK );
	}
    
}