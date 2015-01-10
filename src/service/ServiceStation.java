package service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import beans.*;
import dao.configuration.DaoFactory;

@Path("serviceStation")
public class ServiceStation {

	@GET
	@Path(value="/toutRechercher")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> toutRechercher(){
		List<Station> stations = DaoFactory.getInstance().getStationDAO().toutRechercher();
		//System.out.println("Stations : " + stations.toString() + " - " + stations.size());
		return stations;
	}
	
	@GET
	@Path(value="/rechercher/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> rechercheStation(@PathParam("keyword") String keyword){
		List<Station> stations = DaoFactory.getInstance().getStationDAO().rechercherMotCle(keyword);
		//System.out.println("Stations : " + stations.toString() + " - " + stations.size());
		return stations;
	}
	
	@GET
	@Path(value="/id-{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Station rechercheStationId(@PathParam("id") int id){
		Station s = new Station();
		s.setIdStation(id);
		
		Station station = DaoFactory.getInstance().getStationDAO().rechercher(s);
		//System.out.println("Stations : " + stations.toString() + " - " + stations.size());
		return station;
	}
	
	@GET
	@Path(value="/rechercheAvancee/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> rechercheAvancee(
			@QueryParam("id") int id,
			@QueryParam("ville") String ville,
			@QueryParam("adresse") String adresse,
			@QueryParam("codePostal") int codePostal,
			@QueryParam("numero") int numero,
			@QueryParam("latitude") double latitude,
			@QueryParam("longitude") double longitude){
		Station s = new Station();
		s.setIdStation(id);
		s.setVille(ville);
		s.setAdresse(adresse);
		s.setNumero(numero);
		s.setCodePostal(codePostal);
		s.setNumero(numero);
		s.setLatitude(latitude);
		s.setLongitude(longitude);
		
		List<Station> stations = DaoFactory.getInstance().getStationDAO().rechercheAvancee(s);
		//System.out.println("Stations : " + stations.toString() + " - " + stations.size());
		return stations;
	}
	
}
