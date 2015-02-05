package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	@GET
	@Path(value = "/reserver/{client}/{station}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean reservationComplete(
			@PathParam(value = "client") int idClient,
			@PathParam(value = "station") int idStation) {
		
		return actionReserverRendre(idClient, idStation, 1);
	}
	
	@GET
	@Path(value = "/rendre/{client}/{station}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean rendre(
			@PathParam(value = "client") int idClient,
			@PathParam(value = "station") int idStation,
			@PathParam(value = "vehicule") int idVehicule) {
		
		return actionReserverRendre(idClient, idStation, 0);
	}
	
	/**
	 * Réservation complète :
	 * prend en param une station et un utilisateur
	 * récupère une borne de la station
	 * et selon l'action : réserve (1) ou rend (0)
	 * @param idClient
	 * @param idStation
	 * @param action
	 * @return
	 */
	protected MyBoolean actionReserverRendre(int idClient, int idStation, int action) {
		
		Station rech_s = new Station();
		rech_s.setIdStation(idStation);
		Station station = DaoFactory.getInstance().getStationDAO().rechercher(rech_s);
		
		Client rech_c = new Client();
		rech_c.setIdClient(idClient);
		Client client = DaoFactory.getInstance().getClientDAO().rechercher(rech_c);
		
		boolean done = false;
		
		if(station != null) {
			Borne rech_b = new Borne();
			rech_b.setStation(station);
			List<Borne> bornes = DaoFactory.getInstance().getBorneDAO().rechercheAvancee(rech_b);
			for(Borne borne : bornes) {
				// recherche d'une borne correspondant
				// action = 1 pour réserver donc on cherche les bornes à l'état 0
				// action = 0 pour rendre la voiture, on recherche une borne libre 1
				if(!done && borne.getEtatBorne() == (1-action)) {
					
					if(action == 1) { // RESERVER
						Vehicule rech_v = borne.getVehicule();
						Vehicule vehicule = DaoFactory.getInstance().getVehiculeDAO().rechercher(rech_v);
						Reservation reservation = new Reservation();
						
						// réservation
						vehicule.setDisponible("RESERVE");
						borne.setEtatBorne(1);
						borne.setVehicule(null);
						reservation.setClient(client);
						reservation.setVehicule(vehicule);
						reservation.setDateReservation(new Date());
						// sauvegarde
						DaoFactory.getInstance().getBorneDAO().miseAjour(borne);
						DaoFactory.getInstance().getVehiculeDAO().miseAjour(vehicule);
						DaoFactory.getInstance().getReservationDAO().creation(reservation);
						done = true;
						
					} else { // RENDRE
						Reservation rech_r = new Reservation();
						rech_r.setClient(client);
						Reservation reservation = DaoFactory.getInstance().getReservationDAO().rechercherParClient(rech_r);
						
						Vehicule rech_v = reservation.getVehicule();
						Vehicule vehicule = DaoFactory.getInstance().getVehiculeDAO().rechercher(rech_v);
						
						// mise à jour
						vehicule.setDisponible("LIBRE");
						borne.setEtatBorne(0);
						borne.setVehicule(vehicule);
						// sauvegarde
						DaoFactory.getInstance().getBorneDAO().miseAjour(borne);
						DaoFactory.getInstance().getVehiculeDAO().miseAjour(vehicule);
						DaoFactory.getInstance().getReservationDAO().supprimer(reservation);
						done = true;
					}
					
				}
			}
		}
		
		return new MyBoolean(done);
	}
	
	@GET
	@Path("encours/{client}/")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean reservationEnCours(@PathParam("client") int idClient) {

		Client client = new Client();
		client.setIdClient(idClient);
		
		Reservation rech_r = new Reservation();
		rech_r.setClient(client);
		
		Reservation reservation = DaoFactory.getInstance().getReservationDAO().rechercherParClient(rech_r);
		
		// return true si le client a une réservation en cours
		return new MyBoolean(reservation != null);
		
	}
}
