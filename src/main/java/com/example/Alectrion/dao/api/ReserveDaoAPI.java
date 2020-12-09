package com.example.Alectrion.dao.api;


import com.example.Alectrion.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveDaoAPI extends JpaRepository<Reserve, Reserve.ReservePK> {

    List<Reserve> findByReservePK_Establishment_IdAndReservePK_Hora(Integer est_id, String hora);
    List<Reserve> findByReservePK_Persona_Id(Integer user_id);
    List<Reserve> findByReservePK_Establishment_Id(Integer est_id);
    void deleteByReservePK_Persona_Id(Integer i);
    void deleteByReservePK_Establishment_Id(Integer est_id);
}