package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.configuration.DaoFactory;

@Path("/serviceClient")
public class ServiceClient {
	
	@GET
	@Path(value="/connexionClient/{pseudo}")
	@Produces(MediaType.TEXT_PLAIN)
	public String connexionClient(@PathParam(value="pseudo")String pseudo){
		System.out.println("WEBBBBBBBBBBBBB");
		System.out.println(pseudo);
		return (DaoFactory.getInstance().getClientDAO().connexionClient(pseudo))?"VRAI":"FAUX";
	}
	
}
