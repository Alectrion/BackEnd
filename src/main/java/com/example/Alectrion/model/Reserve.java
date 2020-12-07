package com.example.Alectrion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;



    @EmbeddedId
    private ReservePK reservePK;



    public Reserve( ){
        reservePK = new ReservePK( );
    }

    public Reserve(Persona cliente, Establishment favorito, String hora){
        reservePK = new ReservePK(cliente, favorito, hora);
    }

    Persona getUser( ){
        return reservePK.getPersona( );
    }

    void setUser( Persona persona ){
        reservePK.setPersona( persona );
    }

    Establishment getEstablishment( ){
        return reservePK.getEstablishment();
    }

    void setEstablishment( Establishment establishment ){
        reservePK.setEstablishment( establishment );
    }

    String getHora(){ return reservePK.getHora();}

    void setHora(String hora){ reservePK.setHora(hora); }


    @Override
    public boolean equals( Object object ){
        if( !(object instanceof UserRole) ) return false;
        UserRole userRole = (UserRole) object;
        return getUser( ).getId( ).equals( userRole.getUser( ).getId( ) ) &&
                getEstablishment( ).getId( ).equals( userRole.getRole( ).getId( ) );
    }

    @Override
    public int hashCode( ){
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + getUser( ).hashCode( );
        hash = hash * prime + getEstablishment( ).hashCode( );
        return hash;
    }


    @Embeddable
    public static class ReservePK implements Serializable{

        private static final long serialVersionUID = 1L;



        @ManyToOne( cascade = CascadeType.REMOVE)
        @JoinColumn( name = "user_id" )
        private Persona persona;

        @ManyToOne( cascade = CascadeType.REMOVE)
        @JoinColumn( name = "est_id")
        private Establishment establishment;

        @Column(name = "hora")
        private String hora;

        public ReservePK( ){ }

        public ReservePK( Persona user, Establishment establishment, String hora ){
            this.persona = user;
            this.establishment = establishment;
            this.hora = hora;
        }


        public Persona getPersona( ){
            return persona;
        }

        public void setPersona( Persona user ){
            this.persona = persona;
        }

        public Establishment getEstablishment( ){
            return establishment;
        }

        public void setEstablishment( Establishment establishment ){
            this.establishment = establishment;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }
    }
}
