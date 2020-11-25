package com.example.Alectrion.Service;

import com.example.Alectrion.dao.api.RoleDaoAPI;
import com.example.Alectrion.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService{

    private final RoleDaoAPI roleRepository;

    public RoleService( RoleDaoAPI roleRepository ){
        this.roleRepository = roleRepository;
    }


    public Role findById(Integer id ){
        return roleRepository.findById( id ).orElse( null );
    }

    public List<Role> getAll( ){
        return roleRepository.findAll( );
    }

}
