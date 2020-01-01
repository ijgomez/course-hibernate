package org.course.hibernate;

import java.util.Date;
import java.util.List;

import org.course.hibernate.beans.Event;
import org.course.hibernate.beans.User;
import org.course.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class ExerciseTest {
	
	@Before
	public void beforeTest() throws Exception {

	}
	
	@Test
	public void test() throws Exception {
		Long eventId = crearEvento(new Date(), "Evento uno");
        System.out.println("Creado evento con id " + eventId);
        System.out.println("Mostrando todos los eventos");
        mostrarEventos();
        
        Long personId = crearPersona("nombre", "apellido");
        System.out.println("Creada persona con id " + personId);
        System.out.println("Añadiendo un evento a una persona");
        addPersonToEvent(personId, eventId);
        System.out.println("Añadiendo una dirección de correo electrónico a una persona");
        addEmailToPerson(personId, "abc@xyz.com");
        System.out.println("Añadiendo una dirección de correo electrónico a una persona de otra manera");
        otroAddEmailToPerson(personId, "def@xyz.com");
        System.out.println("Mostrando todas las personas");
        mostrarPersonas();
        
        HibernateUtil.getSessionfactory().close();
        System.out.println("Fin");
		
		
	}
	

    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        User person = (User) session.load(User.class, personId);
        person.getEmailAddresses().add(emailAddress);
        session.getTransaction().commit();
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        User person = (User) session.load(User.class, personId);
        Event event = (Event) session.load(Event.class, eventId);
        person.getEvents().add(event);
        session.getTransaction().commit();

    }

    private Long crearEvento(Date date, String string) {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        Event event = new Event();
        event.setDate(date);
        event.setTitle(string);
        session.save(event);
        session.getTransaction().commit();
        return event.getId();
    }

    private Long crearPersona(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        User person = new User();
        person.setFirstname(firstName);
        person.setLastname(lastName);
        session.save(person);
        session.getTransaction().commit();
        return person.getId();
    }

    private void mostrarEventos() {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Event> lista = session.createQuery("from Event").list();
        session.getTransaction().commit();
        System.out.format("Encontrados %d eventos.\n", lista.size());
        for (Event event : lista) {
            System.out.println(event);
        }
    }

    private void mostrarPersonas() {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<User> lista = session.createQuery("from User").list();
        System.out.format("Encontradas %d personas.\n", lista.size());
        for (User person : lista) {
            System.out.println(person);
        }
        session.getTransaction().commit();
    }

    private void otroAddEmailToPerson(Long personId, String string) {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        User person = (User) session.createQuery("select p from User p left join fetch p.emailAddresses where p.id = :pid").setParameter("pid", personId).uniqueResult();
        person.getEmailAddresses().add(string);
        session.getTransaction().commit();
    }

}
