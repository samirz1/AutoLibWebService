package beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

@XmlRootElement
public class Utilise implements Serializable {

	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Vehicule vehicule;
	private Client client;
	private DateTime date;
	private Borne borneDepart;
	private Borne borneArrivee;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public Utilise() {
		super();
		this.setClient(new Client());
		this.setVehicule(new Vehicule());
		this.setDate(new DateTime());
		this.setBorneDepart(new Borne());
		this.setBorneArrivee(new Borne());
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

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Borne getBorneDepart() {
		return borneDepart;
	}

	public void setBorneDepart(Borne borneDepart) {
		this.borneDepart = borneDepart;
	}

	public Borne getBorneArrivee() {
		return borneArrivee;
	}

	public void setBorneArrivee(Borne borneArrivee) {
		this.borneArrivee = borneArrivee;
	}

}
