/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.course.hibernate.ejb3.sesion.stateless;

import java.util.List;
import javax.ejb.Remote;

import org.course.hibernate.ejb3.persistencia.unouno.bidireccional.PersonaUnoUnoBi;

/**
 *
 * @author user
 */
@Remote
public interface PersonaUnoUnoBidireccionalRemote {

    void create(PersonaUnoUnoBi personaUnoUnoBi);

    void edit(PersonaUnoUnoBi personaUnoUnoBi);

    void remove(PersonaUnoUnoBi personaUnoUnoBi);

    PersonaUnoUnoBi find(Object id);

    List<PersonaUnoUnoBi> findAll();

}
