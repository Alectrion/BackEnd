package com.example.Alectrion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 */
@Entity
@Table( name = "user", schema = "public" )
public class Persona implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @Id
    @SequenceGenerator( name = "USER_USERID_GENERATOR", sequenceName = "public.user_user_id_seq", allocationSize = 1 )
    @GeneratedValue( generator = "USER_USERID_GENERATOR", strategy = GenerationType.SEQUENCE )
    @Column( name = "user_id" )
    private Integer id;

    private String names;

    @JsonIgnore
    private String password;

    private String username;

    private String eMail;
    

    //bi-directional many-to-many association to Role
    @JsonIgnore
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "user_role", joinColumns = { @JoinColumn( name = "user_id" )}
    ,inverseJoinColumns = { @JoinColumn( name = "role_id" ) })

    private List<Role> roles;
    
    @OneToMany(mappedBy = "id_propietario")
    private List<Establishment> establishments;

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

    /**
     * Constructors
     */

    public Persona( ){
        // Default constructor is required
    }

    /**
     * Getters and Setters
     */

    public Integer getId( ){
        return this.id;
    }

    public void setId( Integer id ){
        this.id = id;
    }

    public String getNames( ){
        return this.names;
    }

    public void setNames( String names ){
        this.names = names;
    }

    public String getPassword( ){
        return this.password;
    }

    public void setPassword( String password ){
        this.password = password;
    }

    public String getUsername( ){
        return this.username;
    }

    public void setUsername( String username ){
        this.username = username;
    }

    public List<Role> getRoles( ){
        return this.roles;
    }

    public void setRoles( List<Role> roles ){
        this.roles = roles;
    }
    


    public String getEmail(){ return this.eMail;}

    public void setEmail(String eMail){ this.eMail = eMail;}
    


	/**
     * Methods
     */

    public void addRole( Role role ){
        roles.add( role );
    }

    public boolean hasRole( Role role ){
        return roles.contains( role );
    }

    @Override
    public boolean equals( Object object ){
        if( !(object instanceof Persona) ) return false;
        return id.equals( ((Persona) object).getId( ) );
    }

    @Override
    public int hashCode( ){
        return id;
    }

}