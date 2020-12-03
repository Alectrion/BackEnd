package com.example.Alectrion.dao.api;


import com.example.Alectrion.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveDaoAPI extends JpaRepository<Reserve, Reserve.ReservePK> {

    List<Reserve> findByReservePK_Establishment_IdAndReservePK_Hora(int est_id, String hora);
}
