package com.example.Alectrion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Favorites implements Serializable {


    private static final long serialVersionUID = 1L;



    @EmbeddedId
    private FavoritePK favoritePK;



    public Favorites( ){
        favoritePK = new FavoritePK( );
    }

    public Favorites(Persona cliente, Establishment favorito){
        favoritePK = new FavoritePK(cliente, favorito);
    }

    Persona getUser( ){
        return favoritePK.getPersona( );
    }

    void setUser( Persona persona ){
        favoritePK.setPersona( persona );
    }

    Establishment getEstablishment( ){
        return favoritePK.getEstablishment();
    }

    void setEstablishment( Establishment establishment ){
        favoritePK.setEstablishment( establishment );
    }


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
    private static class FavoritePK implements Serializable{

        private static final long serialVersionUID = 1L;



        @ManyToOne
        @JoinColumn( name = "user_id" )
        private Persona persona;

        @ManyToOne
        @JoinColumn( name = "est_id")
        private Establishment establishment;



        public FavoritePK( ){ }

        public FavoritePK( Persona user, Establishment establishment ){
            this.persona = user;

            this.establishment = establishment;
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
    }

}
