package com.ljq.model;

public class Environment implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String number;
	private String name;
	private String location;
	private String area;
	private String date;
	private String memorable;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMemorable() {
		return memorable;
	}

	public void setMemorable(String memorable) {
		this.memorable = memorable;
	}

	@Override
	public String toString() {
		return " [Repair id=" + id + ", number=" + number + ", name=" + name + ", location=" + location 
				+ ", area=" + area + ", date=" + date + ", memorable=" + memorable + " ]  ";
	}
}