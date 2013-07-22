/**
 * 
 */
package com.tiago.bobby.types;

/**
 * @author tfr_souza
 *
 */
public class Person {
	
	private long id;
	private String nome;
	
	public Person() {}
	
	public Person(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
