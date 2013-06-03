/**
 * 
 */
package org.tiago.bobby.graphdb;

import java.util.ArrayList;
import java.util.List;

import org.tiago.bobby.types.Person;

import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Query.Compare;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author tfr_souza
 *
 */
public class ManagedBobbyGraphDB {

	private TitanGraph bobbyGraph;
	
	public ManagedBobbyGraphDB() {
		bobbyGraph = SimpleBobbyGraphDBFactory.getInstance();
	}
	
	public void addPerson(long id, String name) {
		Vertex person = bobbyGraph.addVertex(null);
		person.setProperty("facebook_id", id);
		person.setProperty("name", name);
		bobbyGraph.commit();
	}
	
	public void addFriend(long id, long idFriend) {
		Vertex person = bobbyGraph.query().has("facebook_id", Compare.EQUAL, id).vertices().iterator().next();
		Vertex personFriend = bobbyGraph.query().has("facebook_id", Compare.EQUAL, idFriend).vertices().iterator().next();
		
		person.addEdge("knows", personFriend);
		bobbyGraph.commit();
	}
	
	public List<Person> listFriends(long id) {
		Vertex person = bobbyGraph.getVertices("facebook_id", id).iterator().next();
		List<Person> list = new ArrayList<Person>();
		Vertex friend = null;
		Person personF = null;
		
		for (Edge edge : person.query().direction(Direction.BOTH).labels("knows").edges()) {
			friend = edge.getVertex(Direction.IN);
			personF = new Person((String)friend.getProperty("name"), (Long)friend.getProperty("facebook_id"));
			list.add(personF);
		}
		
		return list;
	}
	
	public Person load(long id) {
		Person person = null;
		if (bobbyGraph.query().has("facebook_id", Compare.EQUAL, id).vertices().iterator().hasNext()) {
			Vertex v = bobbyGraph.query().has("facebook_id", Compare.EQUAL, id).vertices().iterator().next();
			person = new Person((String)v.getProperty("name"), (Long)v.getProperty("facebook_id"));
		} else {
			person = new Person("pessoa nao encontrada", -1);
		}
		return person;
	}
	
	public void close() {
		if (bobbyGraph != null && bobbyGraph.isOpen()) bobbyGraph.shutdown();
	}
}
