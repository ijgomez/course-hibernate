package org.course.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.course.hibernate.beans.User;
import org.junit.Test;

public class ExerciseTest {
	
	private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("Exercise");

	@Test
	public void test() throws Exception {
		User usuario = crearUser();
        mostrarUsers();
        actualizarUser(usuario.getId());
        mostrarUsers();
        borrarUser(usuario.getId());
        mostrarUsers();
	}
	
	private void actualizarUser(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            User usuario = (User) em.find(User.class, id);
            usuario.setName("Nombre actualizado");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    private void borrarUser(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            User usuario = (User) em.find(User.class, id);
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    private User crearUser() {
        EntityManager em = null;
        User usuario = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            usuario = new User("Uno", "clave uno");
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return usuario;
    }

    @SuppressWarnings("unchecked")
	private void mostrarUsers() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("from User");
            List<User> usuarios = query.getResultList();
            if (usuarios.size() == 0) {
                System.out.println("No hay usuarios que mostrar");
            } else {
                for (User usuario : usuarios) {
                    System.out.println(usuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
