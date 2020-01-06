/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.persistencia.unomuchos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author user
 */
@Entity
public class DireccionUnoMuchos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String calle;
    private Integer numero;
    private PersonaUnoMuchos personaUnoMuchos;

    public DireccionUnoMuchos() {
    }

    public DireccionUnoMuchos(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionUnoMuchos)) {
            return false;
        }
        DireccionUnoMuchos other = (DireccionUnoMuchos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.course.hibernate.ejb3.persistencia.unomuchos.DireccionUnoMuchos[id=" + id + "]";
    }

    @ManyToOne
    public PersonaUnoMuchos getPersonaUnoMuchos() {
        return personaUnoMuchos;
    }

    public void setPersonaUnoMuchos(PersonaUnoMuchos personaUnoMuchos) {
        this.personaUnoMuchos = personaUnoMuchos;
    }
}
