package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.MyBoolean;
import dao.configuration.DaoFactory;

@Path("/serviceClient")
public class ServiceClient {
	
	@GET
	@Path(value="/connexionClient/{pseudo}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean connexionClient(@PathParam(value="pseudo")String pseudo){
		return (DaoFactory.getInstance().getClientDAO().connexionClient(pseudo))?new MyBoolean(true):new MyBoolean(false);
	}
	
}
