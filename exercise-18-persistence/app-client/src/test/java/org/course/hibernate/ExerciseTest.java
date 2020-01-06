package org.course.hibernate;

import java.util.Collection;

import javax.ejb.EJB;

import org.course.hibernate.ejb3.persistencia.muchosmuchos.DireccionMuchosMuchos;
import org.course.hibernate.ejb3.persistencia.muchosmuchos.PersonaMuchosMuchos;
import org.course.hibernate.ejb3.persistencia.simple.User;
import org.course.hibernate.ejb3.persistencia.unomuchos.DireccionUnoMuchos;
import org.course.hibernate.ejb3.persistencia.unomuchos.PersonaUnoMuchos;
import org.course.hibernate.ejb3.persistencia.unouno.bidireccional.DireccionUnoUnoBi;
import org.course.hibernate.ejb3.persistencia.unouno.bidireccional.PersonaUnoUnoBi;
import org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Direccion;
import org.course.hibernate.ejb3.sesion.stateless.PersonaFacadeRemote;
import org.course.hibernate.ejb3.sesion.stateless.PersonaMuchosMuchosRemote;
import org.course.hibernate.ejb3.sesion.stateless.PersonaUnoMuchosRemote;
import org.course.hibernate.ejb3.sesion.stateless.PersonaUnoUnoBidireccionalRemote;
import org.course.hibernate.ejb3.sesion.stateless.PersonaUnoUnoUnidireccionalRemote;
import org.junit.Test;

public class ExerciseTest {
	
	@EJB
	private static PersonaMuchosMuchosRemote personaMuchosMuchosBean;

	@Test
	public void testManyToMany() throws Exception {
		System.out.println("Creando algunos objetos de tipo persona relacionados muchos-muchos en ambos sentidos...");
        for (int i = 0; i < 10; i++) {
            PersonaMuchosMuchos p = new PersonaMuchosMuchos("PersonaMuchosMuchos " + i);
            DireccionMuchosMuchos d1 = new DireccionMuchosMuchos("Abc" + i, i);
            DireccionMuchosMuchos d2 = new DireccionMuchosMuchos("Abc" + (i + 1), i);
            p.getDirecciones().add(d1);
            p.getDirecciones().add(d2);
            d1.getPersonaMuchosMuchos().add(p);
            d2.getPersonaMuchosMuchos().add(p);
            personaMuchosMuchosBean.crearPersona(p);
        }
        System.out.println("Lista de personas en la base de datos");
        DireccionMuchosMuchos d = null;
        for (PersonaMuchosMuchos persona : personaMuchosMuchosBean.findAll()) {
            System.out.println("El nombre de esta persona es " + persona.getNombre());
            Collection<DireccionMuchosMuchos> direcciones = personaMuchosMuchosBean.getDirecciones(persona);
            if (direcciones.size() == 0) {
                System.out.println("Esta persona no tiene direcciones asociadas");
            } else {
                for (DireccionMuchosMuchos direccion : direcciones) {
                    d = direccion;
                    System.out.println("Calle: " + direccion.getCalle() + ".Número: " + direccion.getNumero());
                }
            }
        }
        System.out.println("Mostrando las personas que viven en la dirección con Id " + d.getId() + ", en la calle " + d.getCalle());
        Collection<PersonaMuchosMuchos> vecinos = personaMuchosMuchosBean.getVecinos(d);
        for (PersonaMuchosMuchos persona : vecinos) {
            System.out.println("Nombre: " + persona.getNombre() + ".Id = " + persona.getId());
        }
	}
	
	@EJB
    private static PersonaFacadeRemote personaFacade;
	
	@Test
	public void testFacadeRemote() throws Exception {
        Long clave = 0L;
        System.out.println("Creando algunos objetos de tipo persona...");
        for (int i = 0; i < 10; i++) {
            org.course.hibernate.ejb3.persistencia.simple.User p = new org.course.hibernate.ejb3.persistencia.simple.User("User " + i);
            clave = personaFacade.create(p);
        }
        System.out.println("Lista de personas en la base de datos");
        for (User persona : personaFacade.findAll()) {
            System.out.println(persona);
        }
        System.out.println("Modificando la persona con clave " + clave);
        User p = personaFacade.find(clave);
        System.out.println("El nombre de la perona con clave " + clave + " es " + p.getName());
        p.setName("Otro nombre");
        personaFacade.edit(p);
        p = personaFacade.find(clave);
        System.out.println("Los datos de la persona son ahora: " + p.toString());
        System.out.println("Borrando la persona con clave " + clave);
        p = personaFacade.find(clave);
        personaFacade.remove(p);
        System.out.println("Lista de personas en la base de datos tras borrar a la persona con clave " + clave);
        for (User persona : personaFacade.findAll()) {
            System.out.println(persona);
        }
    }
	
	@EJB
    private static PersonaUnoMuchosRemote personaUnoMuchosBean;
	
	public void testOneToMany() throws Exception {
        System.out.println("Creando algunos objetos de tipo persona relacionados uno-muchos en ambos sentidos...");
        for (int i = 0; i < 10; i++) {
            PersonaUnoMuchos p = new PersonaUnoMuchos("PersonaUnoMuchos " + i);
            DireccionUnoMuchos d1 = new DireccionUnoMuchos("Abc" + i, i);
            DireccionUnoMuchos d2 = new DireccionUnoMuchos("Abc" + (i + 1), i);
            p.getDirecciones().add(d1);
            p.getDirecciones().add(d2);
            d1.setPersonaUnoMuchos(p);
            d2.setPersonaUnoMuchos(p);
            personaUnoMuchosBean.crearPersona(p);
        }
        System.out.println("Lista de personas en la base de datos");
        for (PersonaUnoMuchos persona : personaUnoMuchosBean.findAll()) {
            System.out.println("El nombre de esta persona es " + persona.getNombre());
            Collection<DireccionUnoMuchos> direcciones = personaUnoMuchosBean.getDirecciones(persona);
            if (direcciones.size() == 0) {
                System.out.println("Esta persona no tiene direcciones asociadas");
            } else {
                for (DireccionUnoMuchos direccion : direcciones) {
                    System.out.println("Calle: " + direccion.getCalle() + ".Número: " + direccion.getNumero());
                }
            }
        }
    }

	public void testOneToManyWithError() throws Exception {
        System.out.println("Creando algunos objetos de tipo persona relacionados uno-muchos en ambos sentidos...");
        for (int i = 0; i < 10; i++) {
            PersonaUnoMuchos p = new PersonaUnoMuchos("PersonaUnoMuchos " + i);
            DireccionUnoMuchos d1 = new DireccionUnoMuchos("Abc" + i, i);
            DireccionUnoMuchos d2 = new DireccionUnoMuchos("Abc" + (i + 1), i);
            p.getDirecciones().add(d1);
            p.getDirecciones().add(d2);
            d1.setPersonaUnoMuchos(p);
            d2.setPersonaUnoMuchos(p);
            personaUnoMuchosBean.crearPersona(p);
        }
        System.out.println("Lista de personas en la base de datos");
        for (PersonaUnoMuchos persona : personaUnoMuchosBean.findAll()) {
            System.out.println("El nombre de esta persona es " + persona.getNombre());
            Collection<DireccionUnoMuchos> direcciones = persona.getDirecciones();
            if (direcciones.size() == 0) {
                System.out.println("Esta persona no tiene direcciones asociadas");
            } else {
                for (DireccionUnoMuchos direccion : direcciones) {
                    System.out.println("Calle: " + direccion.getCalle() + ".Número: " + direccion.getNumero());
                }
            }
        }
    }
	
	@EJB
    private static PersonaUnoUnoBidireccionalRemote personaUnoUnoBidireccionalBean;
	
	public void testOneToOneBiDirectional() throws Exception {
        System.out.println("Creando algunos objetos de tipo persona relacionados uno-uno en ambos sentidos...");
        for (int i = 0; i < 10; i++) {
            PersonaUnoUnoBi p = new PersonaUnoUnoBi("PersonaUnoUnoBi " + i);
            DireccionUnoUnoBi d = new DireccionUnoUnoBi("Abc" + i, i);
            p.setDireccion(d);
            d.setPersonaUnoUnoBi(p);
            personaUnoUnoBidireccionalBean.create(p);
        }
        System.out.println("Lista de personas en la base de datos");
        for (PersonaUnoUnoBi persona : personaUnoUnoBidireccionalBean.findAll()) {
            System.out.println("El nombre de esta persona es " + persona.getDireccion().getPersonaUnoUnoBi().getNombre());
        }
    }
	
	@EJB
    private static PersonaUnoUnoUnidireccionalRemote personaUnoUnoUnidireccionalBean;
	
	public void testOneToOneUniDirectional() throws Exception {
        Long clave = 0L;
        System.out.println("Creando algunos objetos de tipo persona relacionados uno-uno en un sólo sentido...");
        for (int i = 0; i < 10; i++) {
            org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona p = new org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona("User " + i);
            Direccion d = new Direccion("Abc" + i, i);
            p.setDireccion(d);
            clave = personaUnoUnoUnidireccionalBean.create(p);
        }
        System.out.println("Lista de personas en la base de datos");
        for (org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona persona : personaUnoUnoUnidireccionalBean.findAll()) {
            System.out.println(persona);
        }
        System.out.println("Modificando la persona con clave " + clave);
        org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona p = personaUnoUnoUnidireccionalBean.find(clave);
        System.out.println("El nombre de la persona con clave " + clave + " es " + p.getNombre());
        System.out.println("La calle de la persona con clave " + clave + " es " + p.getDireccion().getCalle());
        p.setNombre("Otro nombre");
        p.getDireccion().setCalle("Otra calle");
        personaUnoUnoUnidireccionalBean.edit(p);
        p = personaUnoUnoUnidireccionalBean.find(clave);
        System.out.println("Los datos de la persona son ahora: " + p.toString());
        System.out.println("Borrando la persona con clave " + clave);
        p = personaUnoUnoUnidireccionalBean.find(clave);
        personaUnoUnoUnidireccionalBean.remove(p);
        System.out.println("Lista de personas en la base de datos tras borrar a la persona con clave " + clave);
        for (org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona persona : personaUnoUnoUnidireccionalBean.findAll()) {
            System.out.println(persona);
        }
    }
}
