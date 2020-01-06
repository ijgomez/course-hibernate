/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.persistencia.unouno.bidireccional;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author user
 */
@Entity
public class DireccionUnoUnoBi implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String calle;
    private Integer numero;
    private PersonaUnoUnoBi personaUnoUnoBi;

    public DireccionUnoUnoBi() {
    }

    public DireccionUnoUnoBi(String calle, Integer numero) {
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
        if (!(object instanceof DireccionUnoUnoBi)) {
            return false;
        }
        DireccionUnoUnoBi other = (DireccionUnoUnoBi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.course.hibernate.ejb3.persistencia.unouno.bidireccional.DireccionUnoUnoBi[id=" + id + "]";
    }

    @OneToOne(mappedBy = "direccion")
    public PersonaUnoUnoBi getPersonaUnoUnoBi() {
        return personaUnoUnoBi;
    }

    public void setPersonaUnoUnoBi(PersonaUnoUnoBi personaUnoUnoBi) {
        this.personaUnoUnoBi = personaUnoUnoBi;
    }
}
