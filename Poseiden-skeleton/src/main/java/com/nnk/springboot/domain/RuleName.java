package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/***
 * 
 * @author j.c.
 *POJO class RuleName
 *
 */
@Entity
@Table(name = "rule")
public class RuleName {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "name")
	@NotBlank(message = "Name id is mandatory")
	String name;
	
	@Column(name = "description")
	@NotBlank(message = "Name id is mandatory")
	String description;
	
	@Column(name = "json")
	@NotBlank(message = "Name id is mandatory")
	String json;
	
	@Column(name = "template")
	@NotBlank(message = "Name id is mandatory")
	String template;
	
	@Column(name = "sqlStr")
	@NotBlank(message = "Name id is mandatory")
	String sqlStr;
	
	@Column(name = "sqlPart")
	@NotBlank(message = "Name id is mandatory")
	String sqlPart;

	/***
	 * Constructor for test purposes
	 * @param name
	 * @param description
	 * @param json
	 * @param template
	 * @param sqlStr
	 * @param sqlPart
	 */
	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	public RuleName() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

	@Override
	public String toString() {
		return "RuleName [id=" + id + ", name=" + name + ", description=" + description + ", json=" + json
				+ ", template=" + template + ", sqlStr=" + sqlStr + ", sqlPart=" + sqlPart + "]";
	}
	
}
