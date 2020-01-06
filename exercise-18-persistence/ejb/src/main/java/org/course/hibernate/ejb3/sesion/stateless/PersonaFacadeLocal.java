/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.course.hibernate.ejb3.sesion.stateless;

import java.util.List;
import javax.ejb.Local;

import org.course.hibernate.ejb3.persistencia.simple.User;

/**
 *
 * @author user
 */
@Local
public interface PersonaFacadeLocal {

    Long create(User persona);

    void edit(User persona);

    void remove(User persona);

    User find(Object id);

    List<User> findAll();

}
