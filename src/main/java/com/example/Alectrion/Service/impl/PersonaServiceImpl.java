package com.example.Alectrion.Service.impl;

import com.example.Alectrion.commons.GenericServiceImpl;
import com.example.Alectrion.dao.api.PersonaDaoAPI;
import com.example.Alectrion.model.Persona;
import com.example.Alectrion.Service.api.PersonaServiceAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Long> implements PersonaServiceAPI {

	@Autowired
	private PersonaDaoAPI personaDaoAPI;
	
	@Override
	public CrudRepository<Persona, Long> getDao() {
		return personaDaoAPI;
	}

}