package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/***
 * 
 * @author j.c.
 * POJO class CurvePoint
 *
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "curveId")
	@NotBlank(message = "Curve id is mandatory")
	Integer curveId;
	
	@Column(name = "asOfDate")
	Timestamp asOfDate;
	
	@Column(name = "term")
	@NotBlank(message = "Term is mandatory")
	Double term;
	
	@Column(name = "value")
	@NotBlank(message = "Value is mandatory")
	Double value;
	
	@Column(name = "creationDate")
	Timestamp creationDate;

	/***
	 * Constructor for test purposes
	 * @param curveId
	 * @param term
	 * @param value
	 */
	public CurvePoint(int curveId, double term, double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public Timestamp getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "CurvePoint [id=" + id + ", curveId=" + curveId + ", asOfDate=" + asOfDate + ", term=" + term
				+ ", value=" + value + ", creationDate=" + creationDate + "]";
	}
	
	
	
}
