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

import org.course.hibernate.ejb3.persistencia.muchosmuchos.DireccionMuchosMuchos;
import org.course.hibernate.ejb3.persistencia.muchosmuchos.PersonaMuchosMuchos;

/**
 *
 * @author user
 */
@Stateless
public class PersonaMuchosMuchosBean implements PersonaMuchosMuchosRemote, PersonaMuchosMuchosLocal {

    @PersistenceContext
    private EntityManager em;

    public void crearPersona(PersonaMuchosMuchos persona) {
        em.persist(persona);
    }

    public Collection<DireccionMuchosMuchos> getDirecciones(PersonaMuchosMuchos persona) {
        return em.createQuery("select p.direcciones from PersonaMuchosMuchos p where p = :persona").setParameter("persona", persona).getResultList();
    }

    public Collection<PersonaMuchosMuchos> getVecinos(DireccionMuchosMuchos direccion) {
        return em.createQuery("select d.personaMuchosMuchos from DireccionMuchosMuchos d where d = :direccion").setParameter("direccion", direccion).getResultList();
    }

    public List<PersonaMuchosMuchos> findAll() {
        return em.createQuery("select p from PersonaMuchosMuchos p").getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
}
