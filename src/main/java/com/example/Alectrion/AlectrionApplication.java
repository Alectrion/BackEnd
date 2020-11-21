package com.example.Alectrion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Alectrion.model.Persona;
import com.example.Alectrion.pojo.RegisterUserPOJO;

@SpringBootApplication
public class AlectrionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlectrionApplication.class, args);
	}

}
