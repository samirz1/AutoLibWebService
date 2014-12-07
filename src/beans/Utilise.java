package beans;

import java.io.Serializable;

import org.joda.time.DateTime;

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
	}

	public Utilise(Vehicule vehicule, Client client, DateTime date,
			Borne borneDepart, Borne borneArrivee) {
		super();
		this.vehicule = vehicule;
		this.client = client;
		this.date = date;
		this.borneDepart = borneDepart;
		this.borneArrivee = borneArrivee;
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