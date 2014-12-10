package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.configuration.DaoFactory;

@Path("/serviceTest")
public class ServiceTest {
	
	@GET
	@Path(value="/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		String retour = new String("");
		DaoFactory.getInstance().getClientDAO().toutRechercher();
		return retour;
	}
	
}
