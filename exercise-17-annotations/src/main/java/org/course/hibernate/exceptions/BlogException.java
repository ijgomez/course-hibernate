package org.course.hibernate.exceptions;

public class BlogException extends RuntimeException {

	private static final long serialVersionUID = -1591633462716729982L;

	public BlogException(String message) {
		super(message);
	}
	
	public BlogException(String message, Exception e) {
		super(message, e);
	}
}
