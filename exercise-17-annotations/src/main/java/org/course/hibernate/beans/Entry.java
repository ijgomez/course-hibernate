package org.course.hibernate.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entry implements Serializable {

	private static final long serialVersionUID = -8029082270527374942L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String text;
	
	@Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    
    @ManyToOne
    private Blog blog;
    
    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Comment> comments;
	
	/**
	 * new instance
	 */
	public Entry() {

	}
	
	public Entry(String text, Date date) {
		this.text = text;
		this.date = date;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
}
