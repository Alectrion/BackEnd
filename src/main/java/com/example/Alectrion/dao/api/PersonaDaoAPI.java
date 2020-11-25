package com.example.Alectrion.dao.api;
import com.example.Alectrion.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDaoAPI extends JpaRepository<Persona, Integer> {
    Persona findByUsername( String username );
    Persona findById(int id);
}
