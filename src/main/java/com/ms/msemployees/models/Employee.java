package com.ms.msemployees.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Employee class entity")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "The database generated employee ID")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ApiModelProperty(notes = "employee NAME")
	private String name;
	@ApiModelProperty(notes = "employee EMAIL")
	private String email;
	
}
