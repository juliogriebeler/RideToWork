package br.com.juliogriebeler.ridetowork.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ride")
public class Ride {

	private String id;
	private String name;
	private String description;	
	private Date dateCreation;
	private int dishSuitableQty;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public int getDishSuitableQty() {
		return dishSuitableQty;
	}
	public void setDishSuitableQty(int dishSuitableQty) {
		this.dishSuitableQty = dishSuitableQty;
	}
	
	
}