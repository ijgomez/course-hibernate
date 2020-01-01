package org.course.hibernate.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.course.hibernate.beans.User;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	
	private UserDao userDao;
	
	@Before
	public void beforeTest() throws Exception {
		userDao = new UserDao();
		userDao.initialize();
	}
	
	@Test
	public void testListUsers() throws Exception {
		
		List<User> users = userDao.list();
		
		assertNotNull(users);
		assertEquals(2, users.size());

	}

	@Test
	public void testCreateUser() throws Exception {

		User user = userDao.create(new User("one", "password"));
		
		assertNotNull(user);
		assertNotNull(user.getId());

	}
	
	@Test
	public void testReadUser() throws Exception {
		
		User user = userDao.read(1L);
		
		assertNotNull(user);
		assertNotNull(user.getId());
	}
	
	@Test
	public void testUpdateUser() throws Exception {

		User user = userDao.update(1L, "two");
		
		assertNotNull(user);
		assertEquals("two", user.getName());

	}
	
	@Test
	public void testDeleteUser() throws Exception {

		userDao.delete(1L);
		
	}

}
