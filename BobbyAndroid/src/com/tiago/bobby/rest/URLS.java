/**
 * 
 */
package com.tiago.bobby.rest;

/**
 * @author tfr_souza
 *
 */
public interface URLS {

	String HOST = "http://endereco:8080/bobby/api/";
	
	String ADD_PERSON      = HOST + "person";
	String ADD_FRIEND      = HOST + "person/{id}/friend";
	String LIST_FRIENDS    = HOST + "person/{id}/friends";
	String RECOMMENDATIONS = HOST + "person/{id}/friends/recommendations";
	String REMOVE          = HOST + "person/{id}/remove";
	String FIND            = HOST + "person/{id}";
	
}
