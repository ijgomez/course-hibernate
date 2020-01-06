/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.course.hibernate.ejb3.sesion.stateless;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.course.hibernate.ejb3.persistencia.simple.User;

/**
 *
 * @author user
 */
@Stateless
public class PersonaFacade implements PersonaFacadeLocal, PersonaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public Long create(User persona) {
        em.persist(persona);
        return persona.getId();
    }

    public void edit(User persona) {
        em.merge(persona);
    }

    public void remove(User persona) {
        em.remove(em.merge(persona));
    }

    public User find(Object id) {
        return em.find(org.course.hibernate.ejb3.persistencia.simple.User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select object(o) from PersonaSimple as o").getResultList();
    }

}
