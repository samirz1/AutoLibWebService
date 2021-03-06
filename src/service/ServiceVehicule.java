package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.MyBoolean;
import beans.Vehicule;
import dao.configuration.DaoFactory;

@Path("/serviceVehicule")
public class ServiceVehicule {
	
	@GET
	@Path(value="/toutRechercher")
	@Produces(MediaType.APPLICATION_XML)
	public List<Vehicule> toutRechercher(){
		System.out.println("Tout rechercher v�hicule");
		 return DaoFactory.getInstance().getVehiculeDAO().toutRechercher();
	}
	
	@GET
	@Path(value="/rechercher/{idVehicule}")
	@Produces(MediaType.APPLICATION_XML)
	public Vehicule rechercher(@PathParam(value = "idVehicule") String idVehicule){
		Vehicule vehicule = new Vehicule();
		vehicule.setIdVehicule(Integer.parseInt(idVehicule));
		System.out.println("Rechercher v�hicule" + idVehicule);
		return DaoFactory.getInstance().getVehiculeDAO().rechercher(vehicule);
	}
	
	@GET
	@Path(value="/rechercherVehiculeBorne/{idBorne}")
	@Produces(MediaType.APPLICATION_XML)
	public Vehicule rechercherVehiculeBorne(@PathParam(value = "idBorne") String idBorne){
		Vehicule vehicule = new Vehicule();
		vehicule.setIdBorne(Integer.parseInt(idBorne));
		System.out.println("Rechercher v�hicule borne");
		return DaoFactory.getInstance().getVehiculeDAO().rechercherVehiculeBorne(vehicule);
	}
	
	
	@GET
	@Path(value = "/creation/{type_vehicule}/{station}/{idBorne}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean creation(@PathParam(value = "type_vehicule") String type_vehicule,
			@PathParam(value = "station") String station,
			@PathParam(value = "idBorne") String idBorne) {
		System.out.println("Cr�ation v�hicule");
		Vehicule vehicule = new Vehicule();
		vehicule.setTypeVehicule(Integer.parseInt(type_vehicule));
		vehicule.setStation(Integer.parseInt(station));
		vehicule.setIdBorne(Integer.parseInt(idBorne));
		return (DaoFactory.getInstance().getVehiculeDAO().creation(vehicule)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/modifier/{idVehicule}/{ancienIdBorne}/{nouveauIdBorne}/{NouveauStation}/{RFID}/{etatBatterie}/{Disponibilite}/{type_vehicule}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean modifier(@PathParam(value = "idVehicule") String idVehicule,
			@PathParam(value = "ancienIdBorne") String ancienIdBorne,
			@PathParam(value = "nouveauIdBorne") String nouveauIdBorne,
			@PathParam(value = "NouveauStation") String NouveauStation,
			@PathParam(value = "RFID") String RFID,
			@PathParam(value = "etatBatterie") String etatBatterie,
			@PathParam(value = "Disponibilite") String Disponibilite,
			@PathParam(value = "type_vehicule") String type_vehicule) {
		System.out.println("MAJ v�hicule");
		Vehicule vehicule = new Vehicule();
		//On met a jour l'affectation a la borne si besoin
		if(Integer.parseInt(nouveauIdBorne) != Integer.parseInt(ancienIdBorne)){
			DaoFactory.getInstance().getVehiculeDAO().changeAffectation(Integer.parseInt(idVehicule)
					,Integer.parseInt(ancienIdBorne),Integer.parseInt(nouveauIdBorne));
		}
		vehicule.setIdVehicule(Integer.parseInt(idVehicule));
		vehicule.setRfid(Integer.parseInt(RFID));
		vehicule.setEtatBatterie(Integer.parseInt(etatBatterie));
		vehicule.setDisponible(Disponibilite);
		vehicule.setTypeVehicule(Integer.parseInt(type_vehicule));
		vehicule.setStation(Integer.parseInt(NouveauStation));
		vehicule.setIdBorne(Integer.parseInt(nouveauIdBorne));
		return (DaoFactory.getInstance().getVehiculeDAO().miseAjour(vehicule)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}

	@GET
	@Path(value = "/supprimer/{idVehicule}")
	@Produces(MediaType.APPLICATION_XML)
	public MyBoolean supprimer(@PathParam(value = "idVehicule") String idVehicule) {
		System.out.println("Suppression v�hicule");
		Vehicule vehicule = new Vehicule();
		vehicule.setIdVehicule(Integer.parseInt(idVehicule));
		return (DaoFactory.getInstance().getVehiculeDAO().supprimer(vehicule)) ? new MyBoolean(
				true) : new MyBoolean(false);
	}
	
}
