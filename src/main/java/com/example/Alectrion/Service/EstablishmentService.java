package com.example.Alectrion.Service;

import com.example.Alectrion.dao.api.EstablishmentDaoAPI;
import com.example.Alectrion.dao.api.FavoriteDaoAPI;
import com.example.Alectrion.dao.api.ReserveDaoAPI;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.model.Reserve;
import com.example.Alectrion.pojo.RegistrerEstablishmentPOJO;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EstablishmentService {

    private final EstablishmentDaoAPI estRepository;
    private final FavoriteDaoAPI favoriteDaoAPI;
    private final ReserveDaoAPI reserveDaoAPI;

    public EstablishmentService(EstablishmentDaoAPI estRepository, ReserveDaoAPI reserveDaoAPI,
                                FavoriteDaoAPI favoriteDaoAPI){
        this.estRepository = estRepository;
        this.reserveDaoAPI = reserveDaoAPI;
        this.favoriteDaoAPI = favoriteDaoAPI;
    }

    public  Establishment findByEstName(String estName ){
        return estRepository.findByEstName(estName);
    }

    public Establishment findByEstId(int estId){
        return estRepository.findById(estId);
    }

    public void save( Establishment establishment ){
        estRepository.save( establishment );
    }

    public int getScheduleCapacity(int est_id, String horario){
        List<Reserve> reserves = reserveDaoAPI.findByReservePK_Establishment_IdAndReservePK_Hora(est_id, horario);
        int capacity = reserves.size();
        return capacity;
    }


    public boolean isRightEstablishment( RegistrerEstablishmentPOJO est ){
        boolean correctness = est.getEstName() != null && est.getDir() != null && est.getTel() != null;

        if( correctness ){
            correctness = !est.getEstName().trim( ).isEmpty( )
                    && !est.getTel().trim( ).isEmpty( )
                    && !est.getDir().trim( ).isEmpty( );
        }
        return correctness;
    }
    
    public List<Establishment> getAllEstablishments( ){
        return estRepository.findAll();
    }
    
    public void deleteById(Integer est_id) {
        favoriteDaoAPI.deleteByFavoritePK_Establishment_Id(est_id);
        reserveDaoAPI.deleteByReservePK_Establishment_Id(est_id);
        estRepository.deleteById(est_id);
    }

    public Integer[] getHorariosGrafica(Integer est_id) {
        Integer[] nHorarios = new Integer[15];
        String[] horarios = {"7am - 8am",
                "8am - 9am", "9am - 10am", "10am - 11am",
                "11am - 12pm", "12pm - 1pm", "1pm - 2pm",
                "2pm - 3pm", "3pm - 4pm", "4pm - 5pm",
                "5pm - 6pm", "6pm - 7pm", "7pm - 8pm",
                "8pm - 9pm", "9pm - 10pm"};
        for(int i = 0; i < horarios.length; i++){
            List<Reserve> reserves = reserveDaoAPI.findByReservePK_Establishment_IdAndReservePK_Hora(est_id, horarios[i]);
            nHorarios[i] = reserves.size();
        }
        return nHorarios;
    }
}