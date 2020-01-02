package org.course.hibernate.factories;

import org.course.hibernate.services.BlogService;
import org.course.hibernate.services.BlogServiceImpl;

public abstract class BlogServiceFactory {

	private static BlogService instance;
	
	public static BlogService getInstance() {
		if (instance == null) {
			instance = new BlogServiceImpl();
        }
		return instance;
	}
	
}
