package org.course.hibernate.dao;

import java.util.List;

import org.course.hibernate.beans.Event;
import org.course.hibernate.utils.HibernateHook;
import org.course.hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventDao.class);
	
	private SessionFactory sessionFactory;

	public void initialize() {
		Runtime.getRuntime().addShutdownHook(new HibernateHook());
		this.sessionFactory = HibernateUtil.getSessionfactory();
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> list() {
		Session session = null;
		List<Event> events = null;
		Query query;
		
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            query = session.createQuery("from Event");
            events = query.list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
        	LOGGER.error("Ha ocurrido un error ", e);
        	session.getTransaction().rollback();
        	
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return events;
	}
	
	
	public Event create(Event event) {
		Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            session.persist(event);
            
            session.getTransaction().commit();
        } catch (HibernateException e) {
        	 LOGGER.error("Ha ocurrido un error ", e);
            session.getTransaction().rollback();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return event;
	}
	
	public Event read(Long id) {
		Session session = null;
		Event event = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            event = (Event) session.load(Event.class, id);

            session.getTransaction().commit();
        } catch (HibernateException e) {
        	 LOGGER.error("Ha ocurrido un error ", e);
        	 session.getTransaction().rollback();
        	 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return event;
	}
	
	public Event update(Event event) {
		Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            session.persist(event);
            
            session.getTransaction().commit();
        } catch (HibernateException e) {
        	 LOGGER.error("Ha ocurrido un error ", e);
             session.getTransaction().rollback();
             
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return event;
	}
	
	public void delete(Long id) {
		Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            
            session.delete(session.load(Event.class, id));
            
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
