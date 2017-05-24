package com.sabel.meinrestservice;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/haltestelle")
public class Resource {
	private Service service = new Service();
	
	
	@GET
	@Path("/ping")
	@Produces(MediaType.TEXT_PLAIN)
	public String gibServerzeit() {
		return "Die aktuelle Zeit ist: "+new Date().toString();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_XML)
	public List<Haltestelle> getAllStops(){
		return service.receiveAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Haltestelle getOneStop(@PathParam("id")int id) {
		return service.find(id);
	}
	
	@POST
	@Path("/neu")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Haltestelle addStop(@FormParam("name")String name, @FormParam("lon")double lon, @FormParam("lat")double lat) {
		return service.addStop(name, lon, lat);
	}
	
	@DELETE
	@Path("/{id}")
	public Haltestelle delStop(@PathParam("id")int id) {
		return service.delStop(id);
	}
}
