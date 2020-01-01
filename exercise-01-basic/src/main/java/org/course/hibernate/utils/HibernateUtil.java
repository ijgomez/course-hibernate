package org.course.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			// Crear la factoría de sesesiones leyendo la configuración del archivo hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println("Factoría de sesiones creada.");
		} catch (Exception e) {
			System.err.println("La creación de la factoría de sesiones ha fallado." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionfactory().close();
	}
}
