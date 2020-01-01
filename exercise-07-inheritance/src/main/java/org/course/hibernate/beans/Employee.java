package org.course.hibernate.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends User {

	private static final long serialVersionUID = 2508575408382419545L;

	private Double salary = 0.0d;

	public Employee() {
		super();
	}

	public Employee(String name, String password, Double salary) {
		super(name, password);
		this.salary = salary;
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
