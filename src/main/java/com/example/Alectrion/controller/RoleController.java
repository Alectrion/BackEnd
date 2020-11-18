package com.example.Alectrion.controller;

import com.example.Alectrion.Service.RoleService;
import com.example.Alectrion.model.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController{

    private RoleService roleService;


    public RoleController( RoleService roleService ){
        this.roleService = roleService;
    }


    @GetMapping( value = { "/roles" } )
    public List<Role> getAllRoles( ){ return roleService.getAll( );
    }
}
