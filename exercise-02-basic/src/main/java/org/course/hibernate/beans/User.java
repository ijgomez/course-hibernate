package org.course.hibernate.beans;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = -5192213898097992882L;

	private Long id;

	private String firstname;

	private String lastname;

	private int age;

	private Set<Event> events;

	/**
	 * new instance
	 */
	public User() {

	}

	public User(String firstname, String lastname, int age, Set<Event> events) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.events = events;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
}
