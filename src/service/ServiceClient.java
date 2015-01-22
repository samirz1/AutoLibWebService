package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import beans.Client;
import beans.MyBoolean;
import dao.configuration.DaoFactory;

@Path("/serviceClient")
public class ServiceClient {

	@GET
	@Path(value = "/connexionClient/{pseudo}/{pwd}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean connexionClient(@PathParam(value = "pseudo") String pseudo,@PathParam(value = "pwd") String pwd) {
		System.out.println("connexion en cours..");
		return (DaoFactory.getInstance().getClientDAO().connexionClient(pseudo,pwd)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/toutRechercher")
	@Produces(MediaType.APPLICATION_XML)
	public List<Client> toutRechercher() {
		return DaoFactory.getInstance().getClientDAO().toutRechercher();
	}

	@GET
	@Path(value = "/creation/{nom}/{prenom}/{dateNaissance}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean creation(@PathParam(value = "nom") String nom,
			@PathParam(value = "prenom") String prenom,
			@PathParam(value = "dateNaissance") String dateNaissance) {
		System.out.println("CREATION");
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(nom + "   " + prenom + "   " + dateNaissance);
		try {
			client.setDateNaissance(formatter.parse(dateNaissance));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(client.getNom() + "    " + client.getPrenom()
				+ "     " + client.getDateNaissance());
		return (DaoFactory.getInstance().getClientDAO().creation(client)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/modifier/{idClient}/{nom}/{prenom}/{dateNaissance}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean modifier(@PathParam(value = "idClient") String idClient,
			@PathParam(value = "nom") String nom,
			@PathParam(value = "prenom") String prenom,
			@PathParam(value = "dateNaissance") String dateNaissance) {
		System.out.println("MAJ");
		Client client = new Client();
		client.setIdClient(Integer.valueOf(idClient));
		client.setNom(nom);
		client.setPrenom(prenom);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			client.setDateNaissance(formatter.parse(dateNaissance));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (DaoFactory.getInstance().getClientDAO().miseAjour(client)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/supprimer/{idClient}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean supprimer(@PathParam(value = "idClient") String idClient) {
		System.out.println("SUP");
		Client client = new Client();
		client.setIdClient(Integer.valueOf(idClient));
		return (DaoFactory.getInstance().getClientDAO().supprimer(client)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}
	
	@GET
	@Path(value = "/rechercher/{idClient}")
	@Produces(MediaType.APPLICATION_XML)
	public Client rechercher(@PathParam(value = "idClient") String idClient) {
		System.out.println("RECH");
		Client client = new Client();
		client.setIdClient(Integer.valueOf(idClient));
		System.out.println(client.getNom());
		return DaoFactory.getInstance().getClientDAO().rechercher(client);
	
	}
	
	@GET
	@Path(value = "/rechercher/login/{login}")
	@Produces(MediaType.APPLICATION_XML)
	public Client rechercherLogin(@PathParam(value = "login") String login) {
		Client client = new Client();
		client.setLogin(login);
		return DaoFactory.getInstance().getClientDAO().rechercherLogin(client);
	
	}

}
