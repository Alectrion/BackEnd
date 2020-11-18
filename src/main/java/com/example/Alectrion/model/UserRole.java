package com.example.Alectrion.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Private package class for the relation with M...M table
 */

@Entity
@Table( name = "user_role", schema = "public" )
class UserRole implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @EmbeddedId
    private UserRolePK userRolePK;

    /**
     * Constructors
     */

    public UserRole( ){
        userRolePK = new UserRolePK( );
    }

    /**
     * Getters and Setters
     */

    Persona getUser( ){
        return userRolePK.getPersona( );
    }

    void setUser( Persona persona ){
        userRolePK.setPersona( persona );
    }

    Role getRole( ){
        return userRolePK.getRole( );
    }

    void setRole( Role role ){
        userRolePK.setRole( role );
    }

    /**
     * Methods
     */

    @Override
    public boolean equals( Object object ){
        if( !(object instanceof UserRole) ) return false;
        UserRole userRole = (UserRole) object;
        return getUser( ).getId( ).equals( userRole.getUser( ).getId( ) ) &&
                getRole( ).getId( ).equals( userRole.getRole( ).getId( ) );
    }

    @Override
    public int hashCode( ){
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + getUser( ).hashCode( );
        hash = hash * prime + getRole( ).hashCode( );
        return hash;
    }

    /**
     * Private class for primary key
     */

    @Embeddable
    private static class UserRolePK implements Serializable{

        private static final long serialVersionUID = 1L;

        /**
         * Attributes
         */

        @ManyToOne
        @JoinColumn( name = "user_id", insertable = false, updatable = false )
        private Persona persona;

        @ManyToOne
        @JoinColumn( name = "role_id", insertable = false, updatable = false )
        private Role role;

        /**
         * Constructor
         */

        public UserRolePK( ){ }

        public UserRolePK( Persona user, Role role ){
            this.persona = user;

            this.role = role;
        }

        /**
         * Getters and Setters
         */

        public Persona getPersona( ){
            return persona;
        }

        public void setPersona( Persona user ){
            this.persona = persona;
        }

        public Role getRole( ){
            return role;
        }

        public void setRole( Role role ){
            this.role = role;
        }
    }

}
