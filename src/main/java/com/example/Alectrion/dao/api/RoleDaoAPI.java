package com.example.Alectrion.dao.api;

import com.example.Alectrion.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDaoAPI extends JpaRepository<Role, Integer> {

}
