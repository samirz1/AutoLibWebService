package beans;

import java.io.Serializable;

public class Borne implements Serializable {
	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Integer idBorne;
	private Integer etatBorne;
	private Integer station;
	private Vehicule vehicule;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public Borne() {
		super();
	}

	public Borne(Integer idBorne, Integer etatBorne, Integer station,
			Vehicule vehicule) {
		super();
		this.idBorne = idBorne;
		this.etatBorne = etatBorne;
		this.station = station;
		this.vehicule = vehicule;
	}

	// *******************************************
	// GETTERS & SETTERS
	// *******************************************
	public Integer getIdBorne() {
		return idBorne;
	}

	public void setIdBorne(Integer idBorne) {
		this.idBorne = idBorne;
	}

	public Integer getEtatBorne() {
		return etatBorne;
	}

	public void setEtatBorne(Integer etatBorne) {
		this.etatBorne = etatBorne;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
}
