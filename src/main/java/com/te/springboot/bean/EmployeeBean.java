package com.te.springboot.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_info")
@JsonRootName("employee-info")
@JsonPropertyOrder({ "id", "name" })
@XmlRootElement(name = "employee-info")
public class EmployeeBean implements Serializable {

	@Id
	@Column
	@JsonProperty("emp_id")
	private Integer id;

	@Column
	private String name;

	@Column(name = "dob")
	private Date birthDate;

	
	@Column
	private String password;

}
