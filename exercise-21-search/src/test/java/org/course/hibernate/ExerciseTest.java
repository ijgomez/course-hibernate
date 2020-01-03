package org.course.hibernate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.course.hibernate.beans.User;
import org.course.hibernate.utils.HibernateHook;
import org.course.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
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
		crearUsers(1000);
		indexarUsers();
        buscarUsersConAficion("montañismo");
        mostrarUsers();
	}

	private void buscarUsersConAficion(String aficion) {
        Session session = null;
        FullTextSession fts = null;
        try {
            session = sessionFactory.getCurrentSession();
            fts = Search.createFullTextSession(session);
            fts.getTransaction().begin();
            MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31, new String[]{"aficiones"}, new StandardAnalyzer(Version.LUCENE_31));
            org.apache.lucene.search.Query query = parser.parse(aficion);
            org.hibernate.Query hibQuery = fts.createFullTextQuery(query, User.class);
            List<User> usuarios = hibQuery.list();
            System.out.format("Encontrados %d usuarios aficionados al %s%n", usuarios.size(), aficion);
            fts.getTransaction().commit();
        } catch (ParseException ex) {
            fts.getTransaction().rollback();
            Logger.getLogger(ExerciseTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException hibernateException) {
            fts.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + hibernateException);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private void crearUsers(Integer cuantos) {
        Session session = null;
        User usuario = null;
        System.out.format("Creando %d usuarios%n", cuantos);
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            for (int i = 0; i < cuantos; i++) {
                usuario = new User("Uno" + i, "clave " + i, "abcd" + i);
                usuario.setEmail("xyz@abc.com");
                if (i % 10 == 0) {
                    usuario.setHobbies("fútbol, baloncesto, tenis, natación");
                } else {
                    usuario.setHobbies("fútbol, baloncesto, tenis, montañismo");
                }
                session.persist(usuario);
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

    private void indexarUsers() {
        Session session = null;
        FullTextSession fts = null;
        try {
            session = sessionFactory.getCurrentSession();
            fts = Search.createFullTextSession(session);
            fts.getTransaction().begin();
            List<User> usuarios = session.createQuery("from User").list();
            for (User usuario : usuarios) {
                fts.index(usuario);
            }
            fts.getTransaction().commit();
            System.out.format("Indexados %d usuarios%n", usuarios.size());
        } catch (HibernateException hibernateException) {
            fts.getTransaction().rollback();
            System.out.println("Ha ocurrido un error " + hibernateException);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

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
