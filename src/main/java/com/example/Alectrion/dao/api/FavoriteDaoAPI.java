package com.example.Alectrion.dao.api;

import com.example.Alectrion.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteDaoAPI extends JpaRepository<Favorites, Integer> {
}
