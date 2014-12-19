package beans;

import java.io.Serializable;

public class Station implements Serializable {

	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Integer idStation;
	private Double latitude;
	private Double longitude;
	private String adresse;
	private Integer numero;
	private String ville;
	private Integer codePostal;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public Station() {
		super();
	}

	// *******************************************
	// GETTERS & SETTERS
	// *******************************************
	public Integer getIdStation() {
		return idStation;
	}

	public void setIdStation(Integer idStation) {
		this.idStation = idStation;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

}
