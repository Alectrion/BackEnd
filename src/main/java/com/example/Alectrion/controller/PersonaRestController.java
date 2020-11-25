package com.example.Alectrion.controller;

import com.example.Alectrion.Service.PersonaService;
import com.example.Alectrion.Service.RoleService;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.model.Persona;
import com.example.Alectrion.model.Role;
import com.example.Alectrion.pojo.LoginUserPOJO;
import com.example.Alectrion.pojo.RegisterUserPOJO;
import com.sun.el.stream.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
public class PersonaRestController {

	private PersonaService userService;

	private RoleService roleService;

	private PasswordEncoder passwordEncoder;

	public PersonaRestController(PersonaService userService, RoleService roleService, PasswordEncoder passwordEncoder ){
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping( value = { "/registro/nuevo-usuario/rol/{roleId}" } )
	public ResponseEntity<Void> registerNewUser( @PathVariable Integer roleId, @RequestBody RegisterUserPOJO userPOJO ){
		Role role = roleService.findById( roleId );
		Persona existingUser = userService.findByUsername( userPOJO.getUsername( ) );


		if( role == null || existingUser != null || !userService.isRightUser( userPOJO ) ){
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
		}


		Persona newUser = new Persona( );
		newUser.setNames( userPOJO.getNames( ).toUpperCase( ) );
		newUser.setEmail( userPOJO.getEmail().toUpperCase() );
		newUser.setUsername( userPOJO.getUsername( ).toLowerCase( ) );
		newUser.setPassword( passwordEncoder.encode( userPOJO.getPassword( ) ) );
		newUser.setRoles( Collections.singletonList( role ) );
		userService.save( newUser );

		return new ResponseEntity<>( HttpStatus.CREATED );
	}

	@PostMapping( value = { "/registro/nuevo-rol/{roleId}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> registerRoleToUser( @PathVariable Integer roleId, @RequestBody LoginUserPOJO pojo ){
		Role role = roleService.findById( roleId );
		String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
		Persona existingUser = userService.findByUsername( username );
		if( role == null || existingUser.hasRole( role ) ){
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
		}else if( !passwordEncoder.matches( pojo.getPassword( ), existingUser.getPassword( ) ) ){
			return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
		}
		existingUser.addRole( role );
		userService.save( existingUser );
		return new ResponseEntity<>( HttpStatus.CREATED );
	}

	@PutMapping( value = { "/usuario/editar/{Id}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> updateUser(@PathVariable Integer Id,@RequestBody RegisterUserPOJO userPOJO ){
		Persona existingUser = userService.findByUsername( userPOJO.getUsername( ) );

		existingUser.setNames(userPOJO.getNames());
		existingUser.setEmail( userPOJO.getEmail());
		existingUser.setUsername( userPOJO.getUsername());
		userService.save(existingUser);
		return new ResponseEntity<>( HttpStatus.OK );
	}


	@DeleteMapping( value = { "/usuario/delete/{id}" }, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Void> deleteEstablishment( @PathVariable Integer id){
		userService.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@RequestMapping("/cliente")
	public String cliente(){
		return "pagina de usuario";
		
	}

	@RequestMapping("/propietario")
	public String propietario(){
		return "pagina de propietario";
	}

	@RequestMapping("/publico")
	public String publico(){
		return "pagina de usuario";
	}
	
	@GetMapping( value = { "/persona" } )
    public Persona getPersona( ){ return userService.getPersona( );
    }
	
	@GetMapping( value = { "/all" } )
    public List<Persona> getAll(){ return userService.getAll( );
    }
	
	@GetMapping( value = { "/rol" } )
    public List<Role> getAllRole(){ return userService.getAllRole( );
    }

	@GetMapping( value = {"/propietario/establecimiento"})
	public List<Establishment> getEstablishments( ){ return userService.getEstablishments();
	}

}