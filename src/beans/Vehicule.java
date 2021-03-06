package beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehicule implements Serializable {

	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Integer idVehicule;
	private Integer Rfid;
	private Integer etatBatterie;
	private String disponible;
	private Double latitude;
	private Double longitude;
	private Integer typeVehicule;
	private Integer idBorne;
	private Integer station;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public Vehicule() {
		super();
	}

	// *******************************************
	// GETTERS & SETTERS
	// *******************************************
	public Integer getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(Integer idVehicule) {
		this.idVehicule = idVehicule;
	}

	public Integer getRfid() {
		return Rfid;
	}

	public void setRfid(Integer rfid) {
		Rfid = rfid;
	}

	public Integer getEtatBatterie() {
		return etatBatterie;
	}

	public void setEtatBatterie(Integer etatBatterie) {
		this.etatBatterie = etatBatterie;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
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

	public Integer getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(Integer typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	public Integer getIdBorne() {
		return idBorne;
	}

	public void setIdBorne(Integer idBorne) {
		this.idBorne = idBorne;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public static Integer generateRFID() {
		// TODO Auto-generated method stub
		return 22;
	}
	
	
	
}
