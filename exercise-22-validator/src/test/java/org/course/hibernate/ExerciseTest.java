package org.course.hibernate;

import java.util.List;

import org.course.hibernate.beans.User;
import org.course.hibernate.utils.HibernateHook;
import org.course.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.junit.Before;
import org.junit.Test;

public class ExerciseTest {

	private SessionFactory sessionFactory;

	@Before
	public void beforeTest() throws Exception {
		Runtime.getRuntime().addShutdownHook(new HibernateHook());
		sessionFactory = HibernateUtil.getSessionfactory();
	}

	@Test
	public void test() throws Exception {
		validarUser();

		User usuario = crearUser();
		mostrarUsers();
		actualizarUser(usuario.getId());
		mostrarUsers();
		borrarUser(usuario.getId());
		mostrarUsers();
	}

	private void actualizarUser(Long id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			User usuario = (User) session.load(User.class, id);
			usuario.setName("Name actualizado");
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			System.out.println("Ha ocurrido un error " + hibernateException);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private void borrarUser(Long id) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			User usuario = (User) session.load(User.class, id);
			session.delete(usuario);
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			System.out.println("Ha ocurrido un error " + hibernateException);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private User crearUser() {
		Session session = null;
		User usuario = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			usuario = new User("Uno", "clave uno", "abcd");
			usuario.setEmail("xyz@abc.com");
			session.persist(usuario);
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			System.out.println("Ha ocurrido un error " + hibernateException);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	private void mostrarUsers() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from User");
			List<User> usuarios = query.list();
			if (usuarios.size() == 0) {
				System.out.println("No hay usuarios que mostrar");
			} else {
				for (User usuario : usuarios) {
					System.out.println(usuario);
				}
			}
			session.getTransaction().commit();
		} catch (HibernateException hibernateException) {
			session.getTransaction().rollback();
			System.out.println("Ha ocurrido un error " + hibernateException);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private void validarUser() {
		User usuario = new User();
		ClassValidator validadorUser = new ClassValidator(User.class);
		InvalidValue[] mensajes = validadorUser.getInvalidValues(usuario, "direccion");
		for (InvalidValue error : mensajes) {
			System.out.println(error.getMessage());
		}
		usuario = new User("uno", "dos", "abc");
		mensajes = validadorUser.getInvalidValues(usuario, "direccion");
		for (InvalidValue error : mensajes) {
			System.out.println(error.getMessage());
		}
	}
}
