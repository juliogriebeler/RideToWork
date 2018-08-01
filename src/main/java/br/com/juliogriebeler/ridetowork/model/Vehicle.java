package br.com.juliogriebeler.ridetowork.model;

/**
 * @author juliofg
 *
 */
public class Vehicle {

	private String name;
	private int year;
	private int seatsTotal;
	private int seatsAvailable;
	private String description;	
	private User user;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeatsTotal() {
		return seatsTotal;
	}
	public void setSeatsTotal(int seatsTotal) {
		this.seatsTotal = seatsTotal;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}