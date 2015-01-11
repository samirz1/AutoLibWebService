package service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import beans.*;
import dao.configuration.DaoFactory;

@Path("/serviceStation")
public class ServiceStation {

	@GET
	@Path(value="/toutRechercher")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> toutRechercher(){
		List<Station> stations = DaoFactory.getInstance().getStationDAO().toutRechercher();
		System.out.println("Stations : " + stations.toString() + " - " + stations.size());
		return stations;
	}
	
}
