/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.sesion.stateless;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona;

/**
 *
 * @author user
 */
@Stateless
public class PersonaUnoUnoUnidireccionalBean implements PersonaUnoUnoUnidireccionalRemote, PersonaUnoUnoUnidireccionalLocal {

    @PersistenceContext
    private EntityManager em;

    public Long create(Persona persona) {
        em.persist(persona);
        return persona.getId();
    }

    public void edit(Persona persona) {
        em.merge(persona);
    }

    public void remove(Persona persona) {
        em.remove(em.merge(persona));
    }

    public Persona find(Object id) {
        return em.find(org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona.class, id);
    }

    public List<Persona> findAll() {
        return em.createQuery("select object(o) from PesonaUnoUnoUniDireccional as o").getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
}
