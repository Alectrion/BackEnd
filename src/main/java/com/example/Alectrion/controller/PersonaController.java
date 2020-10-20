package com.example.Alectrion.controller;

import com.example.Alectrion.Service.api.PersonaServiceAPI;
import com.example.Alectrion.model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PersonaController {
    
    @Autowired
    private PersonaServiceAPI personaServiceAPI;

    @RequestMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("List", personaServiceAPI.getAll());
        return "index";
    }

    @GetMapping(value = "/save/{id}")
	public String showSave(@PathVariable("id") Long id , Model model) {
		if(id != null && id != 0) {
			model.addAttribute("persona", personaServiceAPI.get(id));
		}else {
			model.addAttribute("persona", new Persona());
		}
		return "save";
	}
	
	@PostMapping(value = "/save")
	public String save(Persona persona, Model model) {
		personaServiceAPI.save(persona);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		personaServiceAPI.delete(id);
		return "redirect:/";
	}
}
