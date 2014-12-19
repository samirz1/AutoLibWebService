package beans;

import java.io.Serializable;

public class Borne implements Serializable {
	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Integer idBorne;
	private Integer etatBorne;
	private Station station;
	private Vehicule vehicule;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public Borne() {
		super();
		this.setVehicule(new Vehicule());
		this.setStation(new Station());
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

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
}
