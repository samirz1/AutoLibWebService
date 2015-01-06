package beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

@XmlRootElement
public class Reservation implements Serializable {

	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Vehicule vehicule;
	private Client client;
	private DateTime dateReservation;
	private DateTime dateEcheance;

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

	public DateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(DateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public DateTime getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(DateTime dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

}
