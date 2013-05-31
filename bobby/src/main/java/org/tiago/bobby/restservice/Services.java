/**
 * 
 */
package org.tiago.bobby.restservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tiago.bobby.types.Friend;
import org.tiago.bobby.types.Friends;
import org.tiago.bobby.types.Person;

/**
 * @author tfr_souza
 *
 */
@Path("/api")
public class Services {

	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPerson(Person person) {
		System.out.println(person.getName());
		System.out.println(person.getId());
		return "HTTP CREATED 201";
	}
	
	@POST
	@Path("/person/{key}/friend")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addFriend(@PathParam("key") long id, Friend friend) {
		System.out.println("id principal: " + id);
		System.out.println("id amigo: " + friend.getId());
		return "HTTP CREATED 201";
	}
	
	@GET
	@Path("/person/{key}/friends")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listFriends(@PathParam("key") long id) {
		System.out.println("id principal: " + id);
		Person p1 = new Person("Tiago", 789);
		Person p2 = new Person("Rayanna", 987);
		
		Friends f = new Friends();
		f.getFriends().add(p1);
		f.getFriends().add(p2);
		
		return Response.ok(f, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/person/{key}/friends/recommendations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommendations(@PathParam("key") long id) {
		Friends f = new Friends();
		return Response.ok(f, MediaType.APPLICATION_JSON).build();
	}
}
