package org.course.hibernate;

import java.util.List;

import org.course.hibernate.beans.Employee;
import org.course.hibernate.beans.Rol;
import org.course.hibernate.beans.User;
import org.course.hibernate.utils.HibernateHook;
import org.course.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		crearUser();
        crearEmployee();
        mostrarUsers();
        mostrarEmployees();
	}

	private Employee crearEmployee() {
        Session session = null;
        Employee empleado = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            empleado = new Employee("Uno", "clave uno", 2000.0d);
            empleado.setRol(new Rol("Rol uno"));
            session.persist(empleado);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            session.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + hibernateException);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return empleado;
    }

    private User crearUser() {
        Session session = null;
        User usuario = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            usuario = new User("Uno", "clave uno");
            usuario.setRol(new Rol("Rol uno"));
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
	private void mostrarEmployees() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            Query query = session.createQuery("from Employee");
            List<Employee> empleados = query.list();
            if (empleados.size() == 0) {
                System.out.println("No hay empleados que mostrar");
            } else {
                for (Employee empleado : empleados) {
                    System.out.println(empleado);
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
}
