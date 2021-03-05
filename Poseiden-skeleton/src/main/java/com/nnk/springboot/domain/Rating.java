package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/***
 * 
 * @author j.c.
 * POJO class Rating
 *
 */
@Entity
@Table(name = "rating")
public class Rating {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "moodysRating")
	String moodysRating;
	
	@Column(name = "sandPRating")
	String sandPRating;
	
	@Column(name = "fitchRating")
	String fitchRating;
	
	@Column(name = "orderNumber")
	Integer orderNumber;

	/***
	 * Constructor for test purposes
	 * @param moodysRating
	 * @param sandPRating
	 * @param fitchRating
	 * @param orderNumber
	 */
	public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
	
	public Rating() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", moodysRating=" + moodysRating + ", sandPRating=" + sandPRating + ", fitchRating="
				+ fitchRating + ", orderNumber=" + orderNumber + "]";
	}
	
}
