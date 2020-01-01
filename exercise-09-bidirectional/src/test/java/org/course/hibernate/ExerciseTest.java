package org.course.hibernate;

import java.util.List;

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
		User usuario = crearUser();
        mostrarUsers();
        actualizarUser(usuario.getId());
        mostrarUsers();
        borrarUser(usuario.getId());
        mostrarUsers();
//        provocarLazyInitializationException();
	}
	
	private void actualizarUser(Long id) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            User usuario = (User) session.load(User.class, id);
            usuario.setName("Name de usuario actualizado");
            Rol[] arrRoles = usuario.getRoles().toArray(new Rol[0]);
            arrRoles[0].setName("Name actualizado");
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
            usuario = new User("Uno", "clave uno");
            Rol rol = new Rol("Rol uno");
            session.persist(usuario);
            session.persist(rol);
            usuario.getRoles().add(rol);
            rol.getUsers().add(usuario);
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

//    @SuppressWarnings("unchecked")
//	private void provocarLazyInitializationException() {
//        Session session = null;
//        try {
//            session = sessionFactory.getCurrentSession();
//            session.getTransaction().begin();
//            Query query = session.createQuery("from Rol");
//            List<Rol> roles = query.list();
//            session.getTransaction().commit();
//            if (roles.size() == 0) {
//                System.out.println("No hay roles que mostrar");
//            } else {
//                for (Rol rol : roles) {
//                    System.out.println(rol);
//                    if (rol.getUsers().size() == 0) {
//                        System.out.println("Este rol no tiene usuarios asociados");
//                    } else {
//                        for (User usuario : rol.getUsers()) {
//                            System.out.println("Name del usuario " + usuario.getName());
//                        }
//                    }
//                }
//            }
//        } catch (HibernateException hibernateException) {
//            session.getTransaction().rollback();
//            System.out.println("Ha ocurrido un error " + hibernateException);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }

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
                    if (usuario.getRoles().size() == 0) {
                        System.out.println("Este usuario no tiene roles asociados");
                    } else {
                        for (Rol rol : usuario.getRoles()) {
                            System.out.println("Name del rol " + rol.getName());
                        }
                    }
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
