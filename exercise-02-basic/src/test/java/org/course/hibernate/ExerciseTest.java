package org.course.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.course.hibernate.beans.Event;
import org.course.hibernate.beans.User;
import org.course.hibernate.dao.EventDao;
import org.course.hibernate.dao.UserDao;
import org.junit.Before;
import org.junit.Test;

public class ExerciseTest {
	
	private UserDao userDao;
	
	private EventDao eventDao;

	@Before
	public void beforeTest() throws Exception {
		userDao = new UserDao();
		userDao.initialize();
		
		eventDao = new EventDao();
		eventDao.initialize();

	}
	
	@Test
	public void test() throws Exception {
		
		Event event = eventDao.create(new Event(new Date(), "Event...."));
		System.out.println("Event create: " + event.getId());
		
		List<Event> events = eventDao.list();
		System.out.format("Encontrados %d eventos.\n", events.size());
		for (Event e : events) {
			System.out.println(e);
		}
		
		User user = userDao.create(new User("Oliver", "Queen", 39, new HashSet<>()));
		System.out.println("User create: " + user.getId());

		user.getEvents().add(event);
		userDao.update(user);
		
		List<User> users = userDao.list();
		System.out.format("Encontradas %d personas.\n", users.size());
		for (User u : users) {
			System.out.println(u.getId() + " " + u.getFirstname() + " " + u.getLastname());
			
		}
		
	}
	
	

}
