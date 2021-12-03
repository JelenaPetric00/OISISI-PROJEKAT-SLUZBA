package model;

public class Address {
	
	private String street;
	private String streetNumber; //
	private String town;
	private String country;
	
	public Address() {}

	public Address(String street, String streetNumber, String town, String country) {
		
		this.street = street;
		this.streetNumber = streetNumber;
		this.town = town;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
