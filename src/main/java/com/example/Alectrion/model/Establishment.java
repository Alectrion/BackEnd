package com.example.Alectrion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Column(name = "muro")
    private String muro;
    @Column(name = "tipo")
    private String tipoEstablecimiento;


    public Persona getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Persona id_propietario) {
        this.id_propietario = id_propietario;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Persona id_propietario;

    public Establishment(){}


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

	public String getMuro() {
		return muro;
	}

	public void setMuro(String muro) {
		this.muro = muro;
	}
    
}