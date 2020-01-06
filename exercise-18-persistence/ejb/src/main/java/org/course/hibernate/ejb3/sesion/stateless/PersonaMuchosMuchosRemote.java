/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.sesion.stateless;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

import org.course.hibernate.ejb3.persistencia.muchosmuchos.DireccionMuchosMuchos;
import org.course.hibernate.ejb3.persistencia.muchosmuchos.PersonaMuchosMuchos;

/**
 *
 * @author user
 */
@Remote
public interface PersonaMuchosMuchosRemote {

    List<PersonaMuchosMuchos> findAll();

    void crearPersona(PersonaMuchosMuchos persona);

    Collection<DireccionMuchosMuchos> getDirecciones(PersonaMuchosMuchos persona);

    Collection<PersonaMuchosMuchos> getVecinos(DireccionMuchosMuchos direccion);
}
