package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.*;
import dao.configuration.DaoFactory;

@Path("/serviceReservation")
public class ServiceReservation {

	@GET
	@Path(value = "/toutRechercher")
	@Produces(MediaType.APPLICATION_XML)
	public List<Reservation> toutRechercher() {
		return DaoFactory.getInstance().getReservationDAO().toutRechercher();
	}

	@GET
	@Path(value = "/creation/{vehicule}/{client}/{date_reservation}/{date_echeance}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean creation(@PathParam(value = "vehicule") int vehicule,
			@PathParam(value = "client") int client,
			@PathParam(value = "date_reservation") String date_reservation,
			@PathParam(value = "date_echeance") String date_echeance) {

		Reservation resa = new Reservation();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Client c = new Client();
			c.setIdClient(client);
			resa.setClient(c);
			// RETROUVER TT LES INFOS CLIENT
			c = DaoFactory.getInstance().getClientDAO().rechercher(c);
			//
			Vehicule v = new Vehicule();
			v.setIdVehicule(vehicule);
			resa.setVehicule(v);
			// RETROUVER TT LES INFOS CLIENT
			v = DaoFactory.getInstance().getVehiculeDAO().rechercher(v);
			//
			// RETROUVER TT LES INFOS VEHICULE
			// remarque : URLEncoder transforme les espaces en +
			resa.setDateReservation(format.parse(date_reservation.replace('+', ' ')));
			resa.setDateEcheance(format.parse(date_echeance.replace('+', ' ')));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (DaoFactory.getInstance().getReservationDAO().creation(resa)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/modifier/{vehicule}/{client}/{date_reservation}/{date_echeance}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean modifier(@PathParam(value = "vehicule") int idVehicule,
			@PathParam(value = "client") int idClient,
			@PathParam(value = "date_reservation") String date_reservation,
			@PathParam(value = "date_echeance") String date_echeance) {

		Reservation resa = new Reservation();
		Client c = new Client();
		c.setIdClient(idClient);
		resa.setClient(c);
		Vehicule v = new Vehicule();
		v.setIdVehicule(idVehicule);
		resa.setVehicule(v);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// remarque : URLEncoder transforme les espaces en +
			resa.setDateReservation(format.parse(date_reservation.replace('+', ' ')));
			resa.setDateEcheance(format.parse(date_echeance.replace('+', ' ')));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return (DaoFactory.getInstance().getReservationDAO().miseAjour(resa)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/supprimer/{vehicule}/{client}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean supprimer(@PathParam(value = "vehicule") int idVehicule,
			@PathParam(value = "client") int idClient
			) {

		Reservation resa = new Reservation();
		Client c = new Client();
		c.setIdClient(idClient);
		Vehicule v = new Vehicule();
		v.setIdVehicule(idVehicule);
		resa.setClient(c);
		resa.setVehicule(v);
		// client.setidClient(Integer.valueOf(idClient));
		return (DaoFactory.getInstance().getReservationDAO().supprimer(resa)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/rechercher/{vehicule}/{client}")
	@Produces(MediaType.APPLICATION_XML)
	public Reservation rechercher(@PathParam(value = "vehicule") int idVehicule,
			@PathParam(value = "client") int idClient) {

		Reservation res = new Reservation();
		Client client = new Client();
		client.setIdClient(idClient);
		res.setClient(client);
		Vehicule v = new Vehicule();
		v.setIdVehicule(idVehicule);
		res.setVehicule(v);

		return DaoFactory.getInstance().getReservationDAO().rechercher(res);
	}

	
}
