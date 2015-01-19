package beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reservation implements Serializable {

	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Vehicule vehicule;
	private Client client;
	private Date dateReservation;
	private Date dateEcheance;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public Reservation() {
		super();
		this.setClient(new Client());
		this.setVehicule(new Vehicule());
	}

	// *******************************************
	// GETTERS & SETTERS
	// *******************************************
	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

}
