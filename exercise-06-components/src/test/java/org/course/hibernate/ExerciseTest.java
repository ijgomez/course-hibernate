package org.course.hibernate;

import java.util.List;
import java.util.Set;

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
		User usuario = crearUsuario();
        mostrarUsuarios();
        actualizarUsuario(usuario.getId());
        mostrarUsuarios();
	}
	
	private void actualizarUsuario(Long id) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            User usuario = (User) session.load(User.class, id);
            usuario.setName("Nombre de usuario actualizado");            
            Set<Rol> roles = usuario.getRoles();
            roles.add(new Rol("Nuevo rol"));
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

    

    private User crearUsuario() {
        Session session = null;
        User usuario = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            usuario = new User("Uno", "clave uno");
            usuario.getRoles().add(new Rol("Rol uno"));
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
	private void mostrarUsuarios() {
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
