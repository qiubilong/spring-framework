package com.experiment.web.vo;

public class AddressVO {
    private String street;
    private String city;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressVO{" +
				"street='" + street + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}