/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.sesion.stateless;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.course.hibernate.ejb3.persistencia.unomuchos.DireccionUnoMuchos;
import org.course.hibernate.ejb3.persistencia.unomuchos.PersonaUnoMuchos;

/**
 *
 * @author user
 */
@Stateless
public class PersonaUnoMuchosBean implements PersonaUnoMuchosRemote, PersonaUnoMuchosLocal{

    @PersistenceContext
    private EntityManager em;

    public List<PersonaUnoMuchos> findAll() {
        return em.createQuery("select object(o) from PersonaUnoMuchos as o").getResultList();
    }

    public Collection<DireccionUnoMuchos> getDirecciones(PersonaUnoMuchos persona) {
        String s = "select p.direcciones from PersonaUnoMuchos p where p = :persona";
        return em.createQuery(s).setParameter("persona", persona).getResultList();
    }

    public void crearPersona(PersonaUnoMuchos persona) {
        em.persist(persona);
    }
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
}
