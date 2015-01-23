package service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import beans.*;
import dao.configuration.DaoFactory;

@Path("/serviceBorne")
public class ServiceBorne {

	@GET
	@Path(value="/toutRechercher")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Borne> toutRechercher(){
		List<Borne> bornes = DaoFactory.getInstance().getBorneDAO().toutRechercher();
		return bornes;
	}
	
	@GET
	@Path(value="/rechercher/{station}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Borne> rechercheStation(@PathParam("station") Integer idStation){
		Borne borne = new Borne();
		borne.getStation().setIdStation(idStation);
		List<Borne> bornes = DaoFactory.getInstance().getBorneDAO().rechercheAvancee(borne);
		return bornes;
	}
	
	@GET
	@Path(value="/id-{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Borne rechercheBorneId(@PathParam("id") int id){
		Borne b = new Borne();
		b.setIdBorne(id);
		
		Borne borne = DaoFactory.getInstance().getBorneDAO().rechercher(b);

		return borne;
	}
	
	@GET
	@Path(value="/rechercheAvancee/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Borne> rechercheAvancee(
			@QueryParam("idBorne") int idBorne,
			@QueryParam("etatBorne") Integer etatBorne,
			@QueryParam("station") Integer station,
			@QueryParam("idVehicule") Integer idVehicule){
		
		Borne b = new Borne();
		b.setIdBorne(idBorne);
		b.setEtatBorne(etatBorne);
		b.getStation().setIdStation(station);
		b.getVehicule().setIdVehicule(idVehicule);
		
		List<Borne> bornes = DaoFactory.getInstance().getBorneDAO().rechercheAvancee(b);
		return bornes;
	}
	
	@GET
	@Path(value="/ajouter/")
	@Produces(MediaType.APPLICATION_JSON)
	public MyBoolean creation(
			@QueryParam("etatBorne") Integer etatBorne,
			@QueryParam("station") Integer station,
			@QueryParam("idVehicule") Integer idVehicule){
		
		Borne b = new Borne();
		b.setEtatBorne(etatBorne);
		b.getStation().setIdStation(station);
		b.getVehicule().setIdVehicule(idVehicule);
		
		return (DaoFactory.getInstance().getBorneDAO().creation(b) ? new MyBoolean(true) : new MyBoolean(false));
	}
	
	@GET
	@Path(value="/modifier/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public MyBoolean modification(
			@QueryParam("idBorne") int idBorne,
			@QueryParam("etatBorne") Integer etatBorne,
			@QueryParam("station") Integer station,
			@QueryParam("idVehicule") Integer idVehicule){
		
		Borne b = new Borne();
		b.setIdBorne(idBorne);
		b.setEtatBorne(etatBorne);
		b.getStation().setIdStation(station);
		b.getVehicule().setIdVehicule(idVehicule);
		
		return (DaoFactory.getInstance().getBorneDAO().miseAjour(b) ? new MyBoolean(true) : new MyBoolean(false));
	}
	
	@GET
	@Path(value="/supprimer/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public MyBoolean suppression(
			@PathParam("id") int id){
		Borne b = new Borne();
		b.setIdBorne(id);
		
		return (DaoFactory.getInstance().getBorneDAO().supprimer(b) ? new MyBoolean(true) : new MyBoolean(false));
	}
	
}
