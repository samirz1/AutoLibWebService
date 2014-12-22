package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import beans.Borne;
import dao.configuration.DaoFactory;

@Path("/serviceVehicule")
public class ServiceVehicule {
	
	@GET
	@Path(value="/toutRechercher")
	@Produces(MediaType.TEXT_PLAIN)
	public String toutRechercher(){
		String retour = new String("");
		DaoFactory.getInstance().getVehiculeDAO().toutRechercher();
		return retour;
	}
	
	@GET
	@Path(value="/rechercherVehiculeBorne")
	@Produces(MediaType.TEXT_PLAIN)
	public String rechercherVehiculeBorne(@QueryParam("idBorne") int idBorne){
		String retour = new String("");
		List<Borne> bornes=DaoFactory.getInstance().getBorneDAO().toutRechercher();
		DaoFactory.getInstance().getVehiculeDAO().rechercherVehiculeBorne(bornes.get(idBorne));
		return retour;
	}
	
}
