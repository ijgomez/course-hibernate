/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.persistencia.unouno.bidireccional;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author user
 */
@Entity
public class PersonaUnoUnoBi implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private DireccionUnoUnoBi direccion;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public DireccionUnoUnoBi getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionUnoUnoBi direccion) {
        this.direccion = direccion;
    }

    public PersonaUnoUnoBi() {
    }

    public PersonaUnoUnoBi(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof PersonaUnoUnoBi)) {
            return false;
        }
        PersonaUnoUnoBi other = (PersonaUnoUnoBi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.course.hibernate.ejb3.persistencia.unouno.bidireccional.PersonaBi[id=" + id + "]";
    }
}
