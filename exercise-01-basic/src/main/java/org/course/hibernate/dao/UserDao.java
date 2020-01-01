package org.course.hibernate.dao;

import java.util.List;

import org.course.hibernate.beans.User;
import org.course.hibernate.utils.HibernateHook;
import org.course.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
	
	private SessionFactory sessionFactory;

	public void initialize() {
		Runtime.getRuntime().addShutdownHook(new HibernateHook());
		this.sessionFactory = HibernateUtil.getSessionfactory();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		Session session = null;
		List<User> users = null;
		Query query;
		
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            query = session.createQuery("from User");
            users = query.list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
        	LOGGER.error("Ha ocurrido un error ", e);
        	session.getTransaction().rollback();
        	
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
	}
	
	
	public User create(User u) {
		Session session = null;
        User user = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            user = new User(u.getName(), u.getPassword());
            session.persist(user);
            
            session.getTransaction().commit();
        } catch (HibernateException e) {
        	 LOGGER.error("Ha ocurrido un error ", e);
            session.getTransaction().rollback();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
	}
	
	public User read(Long id) {
		Session session = null;
		User user = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            user = (User) session.load(User.class, id);

            session.getTransaction().commit();
        } catch (HibernateException e) {
        	 LOGGER.error("Ha ocurrido un error ", e);
        	 session.getTransaction().rollback();
        	 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
	}
	
	public User update(Long id, String name) {
		Session session = null;
		User user = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            user = (User) session.load(User.class, id);
            user.setName(name);
            
            session.getTransaction().commit();
        } catch (HibernateException e) {
        	 LOGGER.error("Ha ocurrido un error ", e);
             session.getTransaction().rollback();
             
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
	}
	
	public void delete(Long id) {
		Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            session.delete(session.load(User.class, id));
            
            session.getTransaction().commit();
        } catch (HibernateException e) {
       	 	LOGGER.error("Ha ocurrido un error ", e);
            session.getTransaction().rollback();
            
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
	}
	
}
