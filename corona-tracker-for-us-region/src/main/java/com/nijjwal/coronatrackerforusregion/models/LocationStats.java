package com.nijjwal.coronatrackerforusregion.models;

public class LocationStats {
	private String stateOrProvince;
	private String city;
	private String date;
	private String country;
	private String latestTotalCases;

	public LocationStats() {

	}

	public LocationStats(String stateOrProvince, String city, String date, String country, String latestTotalCases) {
		super();
		this.stateOrProvince = stateOrProvince;
		this.city = city;
		this.date = date;
		this.country = country;
		this.latestTotalCases = latestTotalCases;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(String latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	@Override
	public String toString() {
		return "Current date and time: " + java.time.LocalDate.now() + " " + java.time.LocalTime.now()
				+ ", State or Province: " + getStateOrProvince() + "," + "City:" + getCity() + ","
				+ "Total no of confirmed cases as of " + getDate() + ": " + getLatestTotalCases();
	}
}

