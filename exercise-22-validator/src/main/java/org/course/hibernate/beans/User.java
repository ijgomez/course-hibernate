package org.course.hibernate.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -5192213898097992882L;

	@Id
    @GeneratedValue
	private Long id;

	private String name;

	private String password;

	@NotNull(message="La dirección de una persona no puede ser nula")
    @Length(min = 4, max = 10, message = "La dirección debe estar comprendida entre 4 y 10 caracteres")
	private String address;

	@Email(message="El formato es erróneo")
	private String email;

	/**
	 * new instance
	 */
	public User() {

	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String password, String address) {
		super();
		this.name = name;
		this.password = password;
		this.address = address;
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
