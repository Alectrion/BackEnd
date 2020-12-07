package com.example.Alectrion.dao.api;

import com.example.Alectrion.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstablishmentDaoAPI extends JpaRepository<Establishment, Integer> {

    Establishment findByEstName(String estName);
    Establishment findById(int id);
    void deleteById_propietario(int user_id);
}