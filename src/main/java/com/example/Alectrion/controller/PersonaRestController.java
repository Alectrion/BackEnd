package com.example.Alectrion.controller;

import com.example.Alectrion.Service.EstablishmentService;
import com.example.Alectrion.Service.PersonaService;
import com.example.Alectrion.Service.RoleService;
import com.example.Alectrion.model.*;
import com.example.Alectrion.pojo.AddFavoritePOJO;
import com.example.Alectrion.pojo.LoginUserPOJO;
import com.example.Alectrion.pojo.RegisterUserPOJO;
import com.example.Alectrion.pojo.ReservePOJO;
import com.sun.el.stream.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class PersonaRestController {

	private PersonaService userService;

	private RoleService roleService;

	private PasswordEncoder passwordEncoder;

	private EstablishmentService establishmentService;

	public PersonaRestController(PersonaService userService, RoleService roleService, PasswordEncoder passwordEncoder,EstablishmentService establishmentService ){
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
		this.establishmentService = establishmentService;
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

	@PutMapping( value = { "/usuario/editar/{Id}" })
	public ResponseEntity<Void> updateUser(@PathVariable Integer Id,@RequestBody RegisterUserPOJO userPOJO ){
		Persona existingUser = userService.findByUsername( userPOJO.getUsername( ) );

		existingUser.setNames(userPOJO.getNames());
		existingUser.setEmail( userPOJO.getEmail());
		existingUser.setUsername( userPOJO.getUsername());
		userService.save(existingUser);
		return new ResponseEntity<>( HttpStatus.OK );
	}

	@PostMapping(value = { "/cliente/establecimientos/favoritos"})
	public ResponseEntity<Void> addFavorite( @RequestBody AddFavoritePOJO addPOJO ){

		Persona existingUser = userService.findById( addPOJO.getUserID() );
		Establishment existingEstablishment = establishmentService.findByEstId( addPOJO.getEstID());
		Favorites favorite = new Favorites( existingUser, existingEstablishment);
		userService.saveFavorite( favorite );

		return new ResponseEntity<>( HttpStatus.OK );
	}

	@PostMapping(value = { "/cliente/establecimientos/reservas"})
	public ResponseEntity<Void> addReserve( @RequestBody ReservePOJO reservePOJO ){

		Persona existingUser = userService.findById( reservePOJO.getUserID() );
		Establishment existingEstablishment = establishmentService.findByEstId( reservePOJO.getEstID());
		String hora = reservePOJO.getHorario();
		Reserve reserve = new Reserve(existingUser, existingEstablishment, hora);
		userService.saveReserve( reserve );

		return new ResponseEntity<>( HttpStatus.OK );
	}

	@DeleteMapping(value = { "/cliente/establecimientos/qfavoritos"})
	public ResponseEntity<Void> qFavorite( @RequestBody AddFavoritePOJO addPOJO ){

		Persona existingUser = userService.findById( addPOJO.getUserID() );
		Establishment existingEstablishment = establishmentService.findByEstId( addPOJO.getEstID());
		Favorites favorite = new Favorites( existingUser, existingEstablishment);
		userService.deleteFavorite( favorite );

		return new ResponseEntity<>( HttpStatus.OK );
	}

	@DeleteMapping(value = { "/cliente/establecimientos/qreserva"})
	public ResponseEntity<Void> qReserva( @RequestBody ReservePOJO addPOJO ){

		Persona existingUser = userService.findById( addPOJO.getUserID() );
		Establishment existingEstablishment = establishmentService.findByEstId( addPOJO.getEstID());
		String hora = addPOJO.getHorario();
		Reserve reserve = new Reserve( existingUser, existingEstablishment, hora);
		userService.deleteReserve( reserve );

		return new ResponseEntity<>( HttpStatus.OK );
	}

	@GetMapping( value = { "/cliente/establecimientos/misfavoritos/{id}"} )
		public List<Establishment> getFavorites( @PathVariable Integer id){
		List<Establishment> temp = userService.findFavorites(id);
		return temp;
	}

	@GetMapping( value = { "/cliente/establecimientos/misreservas/{id}"} )
	public List<ReservePOJO> getReserves( @PathVariable Integer id){
		List<Reserve> temp = userService.findReserves(id);
		List<ReservePOJO> pojo = new ArrayList<>();
		for (Reserve r: temp
			 ) {
			ReservePOJO a = new ReservePOJO(r.getUser().getId(), r.getEstablishment().getId(), r.getHora());
			pojo.add(a);
		}
		return pojo;
	}

	@Transactional
	@DeleteMapping( value = { "/usuario/delete/{id}" } )
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