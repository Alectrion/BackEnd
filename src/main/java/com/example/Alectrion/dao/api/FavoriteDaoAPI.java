package com.example.Alectrion.dao.api;

import com.example.Alectrion.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteDaoAPI extends JpaRepository<Favorites, Favorites.FavoritePK> {


    List<Favorites> findByFavoritePK_Persona_Id(int id);
}
