/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.ejb3.persistencia.unomuchos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity
public class PersonaUnoMuchos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private Collection<DireccionUnoMuchos> direcciones;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "personaUnoMuchos")
    public Collection<DireccionUnoMuchos> getDirecciones() {
        if (direcciones == null) {
            direcciones = new ArrayList<DireccionUnoMuchos>();
        }
        return direcciones;
    }

    public void setDirecciones(Collection<DireccionUnoMuchos> direcciones) {
        this.direcciones = direcciones;
    }

    public PersonaUnoMuchos() {
    }

    public PersonaUnoMuchos(String nombre) {
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
        if (!(object instanceof PersonaUnoMuchos)) {
            return false;
        }
        PersonaUnoMuchos other = (PersonaUnoMuchos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "El nombre de este PersonaUnoMuchos es " + getNombre();
    }
}
