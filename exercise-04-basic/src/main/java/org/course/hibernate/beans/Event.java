package org.course.hibernate.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Event implements Serializable {

	private static final long serialVersionUID = -3242341449503316081L;

	private Long id;

	private String title;

	private Date date;
	
	private Set<User> participants;

	/**
	 * new instance
	 */
	public Event() {

	}

	public Event(Date date, String title) {
		super();
		this.date = date;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Set<User> getParticipants() {
		return participants;
	}
	
	public void setParticipants(Set<User> participants) {
		this.participants = participants;
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
