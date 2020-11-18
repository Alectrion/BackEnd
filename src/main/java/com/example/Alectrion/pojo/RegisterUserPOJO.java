package com.example.Alectrion.pojo;

public class RegisterUserPOJO{

    private String names;
    private String password;
    private String eMail;
    private String username;



    public String getNames( ){
        return names;
    }

    public void setNames( String names ){
        this.names = names;
    }

    public String getPassword( ){
        return password;
    }

    public void setPassword( String password ){
        this.password = password;
    }

    public String getEmail( ){
        return eMail;
    }

    public void setEmail( String eMail ){
        this.eMail = eMail;
    }

    public String getUsername( ){
        return username;
    }

    public void setUsername( String username ){
        this.username = username;
    }
}

