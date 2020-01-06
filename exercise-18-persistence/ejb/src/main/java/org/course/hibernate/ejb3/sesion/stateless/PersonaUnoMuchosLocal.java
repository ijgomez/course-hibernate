/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.sesion.stateless;

import java.util.Collection;
import javax.ejb.Local;

import org.course.hibernate.ejb3.persistencia.unomuchos.DireccionUnoMuchos;
import org.course.hibernate.ejb3.persistencia.unomuchos.PersonaUnoMuchos;

/**
 *
 * @author user
 */
@Local
public interface PersonaUnoMuchosLocal {

    void crearPersona(PersonaUnoMuchos persona);

    Collection<DireccionUnoMuchos> getDirecciones(PersonaUnoMuchos persona);
}
