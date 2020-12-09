package com.example.Alectrion.pojo;

public class DatosPOJO {
	
	private int userID;
	private int estID;
	private String estName;
    private String dir;
    private String tipoEstablecimiento;
    private String horario;
    
    
	public DatosPOJO(int userID, int estID, String estName, String dir, String tipoEstablecimiento, String horario) {
		this.userID = userID;
		this.estID = estID;
		this.estName = estName;
		this.dir = dir;
		this.tipoEstablecimiento = tipoEstablecimiento;
		this.horario = horario;
	}
	
	
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public int getEstID() {
		return estID;
	}


	public void setEstID(int estID) {
		this.estID = estID;
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
	public String getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}
	public void setTipoEstablecimiento(String tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
    

}
