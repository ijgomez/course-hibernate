package org.course.hibernate.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event implements Serializable {

	private static final long serialVersionUID = -3242341449503316081L;

	private Long id;

	private String title;

	private Date date;

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
