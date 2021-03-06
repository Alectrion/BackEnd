package com.example.Alectrion.model;

import javax.persistence.*;

@Entity
@Table( name = "establishment", schema = "public" )
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column( name = "est_id" )
    private Integer id;

    @Column(name = "estName")
    private String estName;
    @Column(name = "dir")
    private String dir;
    @Column(name = "tel")
    private String tel;
    @Column(name = "cupo_max")
    private int cupoMax;
    @Column(name = "tipo")
    private String tipoEstablecimiento;
    @Column(name = "propietario")
    private Integer id_propietario;

    public Establishment(){}

    public Integer getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Integer id_propietario) {
        this.id_propietario = id_propietario;
    }


/*
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Propietario propietario;

public Establishment(Propietario propietario){
    this.propietario = propietario;
}
*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstName() {
        return estName;
    }

    public void setEstName(String estName) {
        this.estName = estName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }
/*
    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

*/
}
