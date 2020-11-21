package com.example.Alectrion.Service;

import com.example.Alectrion.dao.api.EstablishmentDaoAPI;
import com.example.Alectrion.model.Establishment;
import com.example.Alectrion.pojo.RegistrerEstablishmentPOJO;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EstablishmentService {

    private final EstablishmentDaoAPI estRepository;

    public EstablishmentService(EstablishmentDaoAPI estRepository){
        this.estRepository = estRepository;
    }

    public  Establishment findByEstName(String estName ){
        return estRepository.findByEstName(estName);
    }

    public void save( Establishment establishment ){
        estRepository.save( establishment );
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
        estRepository.deleteById(est_id);
    }
    
}