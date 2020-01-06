/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.course.hibernate.ejb3.sesion.stateless;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.course.hibernate.ejb3.persistencia.unouno.bidireccional.PersonaUnoUnoBi;

/**
 *
 * @author user
 */
@Stateless
public class PersonaUnoUnoBidireccionalBean implements PersonaUnoUnoBidireccionalLocal, PersonaUnoUnoBidireccionalRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(PersonaUnoUnoBi personaUnoUnoBi) {
        em.persist(personaUnoUnoBi);
    }

    public void edit(PersonaUnoUnoBi personaUnoUnoBi) {
        em.merge(personaUnoUnoBi);
    }

    public void remove(PersonaUnoUnoBi personaUnoUnoBi) {
        em.remove(em.merge(personaUnoUnoBi));
    }

    public PersonaUnoUnoBi find(Object id) {
        return em.find(org.course.hibernate.ejb3.persistencia.unouno.bidireccional.PersonaUnoUnoBi.class, id);
    }

    public List<PersonaUnoUnoBi> findAll() {
        return em.createQuery("select object(o) from PersonaUnoUnoBi as o").getResultList();
    }

}
