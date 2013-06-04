/**
 * 
 */
package org.tiago.bobby.restservice;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tiago.bobby.graphdb.ManagedBobbyGraphDB;
import org.tiago.bobby.types.Friend;
import org.tiago.bobby.types.Friends;
import org.tiago.bobby.types.Person;

import com.google.common.util.concurrent.Service;

/**
 * @author tfr_souza
 *
 */
@Path("/api")
public class Services {
	
	private Logger logger = Logger.getLogger(Service.class.getName());

	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPerson(Person person) {
		ManagedBobbyGraphDB bobbyGraph = new ManagedBobbyGraphDB();
		bobbyGraph.addPerson(person.getId(), person.getName());
		logger.info("[INSERT] - Name: " + person.getName() + " | ID: " + person.getId());
		return "HTTP CREATED 201";
	}
	
	@POST
	@Path("/person/{key}/friend")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addFriend(@PathParam("key") long id, Friend friend) {
		ManagedBobbyGraphDB bobbyGraph = new ManagedBobbyGraphDB();
		bobbyGraph.addFriend(id, friend.getId());
		logger.info("[ADD FRIEND] - ID: " + id + " | ID FRIEND: " + friend.getId());
		return "HTTP CREATED 201";
	}
	
	@GET
	@Path("/person/{key}/friends")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listFriends(@PathParam("key") long id) {
		ManagedBobbyGraphDB bobbyGraph = new ManagedBobbyGraphDB();
		List<Person> list = bobbyGraph.listFriends(id); 
		
		Friends f = new Friends();
		f.setFriends(list);
		
		logger.info("[FRIENDS] - Amigos do ID: " + id);
		
		return Response.ok(f, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/person/{key}/friends/recommendations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendations(@PathParam("key") long id) {
		ManagedBobbyGraphDB bobbyGraph = new ManagedBobbyGraphDB();
		List<Person> suggests = bobbyGraph.recommendations(id);
		Friends f = new Friends();
		f.setFriends(suggests);
		logger.info("[RECOMMENDATIONS] - Recomendacoes para o ID: " + id);
		return Response.ok(f, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/person/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("key") long id) {
		ManagedBobbyGraphDB bobbyGraph = new ManagedBobbyGraphDB();
		Person p = bobbyGraph.load(id);
		return Response.ok(p, MediaType.APPLICATION_JSON).build();
	}
}
