/**
 * 
 */
package org.tiago.bobby.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author tfr_souza
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlElement(required = true)
	private String name;
	@XmlElement(name = "facebook_id", required = true)
	private long id;
	
	public Person() {}
	
	public Person(String name, long id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Person)) return false;
		if (((Person)obj) == this) return true;
		
		return ((Person)obj).getId() == this.id;
	}
}
