/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.sesion.stateless;

import java.util.List;
import javax.ejb.Remote;

import org.course.hibernate.ejb3.persistencia.unouno.unidireccional.Persona;

/**
 *
 * @author user
 */
@Remote
public interface PersonaUnoUnoUnidireccionalRemote {

    Long create(Persona persona);

    void edit(Persona persona);

    void remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll();
}
