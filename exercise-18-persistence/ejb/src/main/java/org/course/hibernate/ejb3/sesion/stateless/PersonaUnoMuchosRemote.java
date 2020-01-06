/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.sesion.stateless;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

import org.course.hibernate.ejb3.persistencia.unomuchos.DireccionUnoMuchos;
import org.course.hibernate.ejb3.persistencia.unomuchos.PersonaUnoMuchos;

/**
 *
 * @author user
 */
@Remote
public interface PersonaUnoMuchosRemote {

    void crearPersona(PersonaUnoMuchos persona);

    List<PersonaUnoMuchos> findAll();

    Collection<DireccionUnoMuchos> getDirecciones(PersonaUnoMuchos persona);
}
