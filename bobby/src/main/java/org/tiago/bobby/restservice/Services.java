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
import org.tiago.bobby.types.Suggestion;
import org.tiago.bobby.types.Suggestions;

import com.google.common.util.concurrent.Service;

/**
 * @author tfr_souza
 *
 */
@Path("/api")
public class Services {
	
	private Logger logger = Logger.getLogger(Service.class.getName());
	private ManagedBobbyGraphDB bobbyGraph = new ManagedBobbyGraphDB();

	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPerson(Person person) {
		String retorno = bobbyGraph.addPerson(person.getId(), person.getName());
		if (retorno.contains("201"))
			logger.info("[INSERT] - Name: " + person.getName() + " | ID: " + person.getId());
		else
			logger.info("[INSERT] - " + retorno);
		return "HTTP " + retorno;
	}
	
	@POST
	@Path("/person/{key}/friend")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addFriend(@PathParam("key") long id, Friend friend) {
		String retorno = bobbyGraph.addFriend(id, friend.getId());
		if (retorno.contains("201"))
			logger.info("[ADD FRIEND] - ID: " + id + " | ID FRIEND: " + friend.getId());
		else
			logger.info("[ADD FRIEND] - " + retorno);
		return "HTTP " + retorno;
	}
	
	@GET
	@Path("/person/{key}/friends")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listFriends(@PathParam("key") long id) {
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
//		List<Suggestion> suggests = bobbyGraph.recommendations(id);
		List<Person> suggests = bobbyGraph.recommendations(id);
//		Suggestions s = new Suggestions();
//		s.setRecommendations(suggests);
		Friends f = new Friends();
		f.setFriends(suggests);
		logger.info("[RECOMMENDATIONS] - Recomendacoes para o ID: " + id);
		return Response.ok(f, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/person/{key}/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removePerson(@PathParam("key") long id) {
		String retorno = bobbyGraph.remove(id);
		if (retorno.contains("201"))
			logger.info("[REMOVE] - ID: " + id);
		else
			logger.info("[REMOVE] - " + retorno);
		return "HTTP " + retorno;
	}
	
	@GET
	@Path("/person/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("key") long id) {
		Person p = bobbyGraph.load(id);
		return Response.ok(p, MediaType.APPLICATION_JSON).build();
	}
}
