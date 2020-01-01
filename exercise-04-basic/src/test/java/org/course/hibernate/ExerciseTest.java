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
        Long personId = crearPersona("nombre", "apellido");
        System.out.println("Creada persona con id " + personId);
        System.out.println("Añadiendo un evento a una persona");
        addPersonToEvent(personId, eventId);
        System.out.println("Mostrando todos los eventos");
        mostrarEventos();
        HibernateUtil.getSessionfactory().close();
        System.out.println("Fin");
	}
	

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionfactory().getCurrentSession();
        session.beginTransaction();
        User person = (User) session.load(User.class, personId);
        Event event = (Event) session.load(Event.class, eventId);
        
        person.getEvents().add(event);
        event.getParticipants().add(person);

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
        System.out.format("Encontrados %d eventos.\n", lista.size());
        for (Event event : lista) {
            System.out.println(event);
            if (event.getParticipants().size()>0) {
                System.out.format("Este evento tiene %d participantes\n",event.getParticipants().size());
            }
        }
        session.getTransaction().commit();
    }
    
}
