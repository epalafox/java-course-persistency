package models;

import org.bson.types.ObjectId;

public class Auto {
	public ObjectId _id;
	public int id;
	public int year;
	public String brand;
	public String model;
	public float price;
	public int km;
	public String extColor;
	public String intColor;
	public Transmision trans;
	public float liters;
	public int doors;
	public String getNombre() {
		return Integer.toString(year) + " " + brand + " " + model + " " +
				(trans == Transmision.auto ? "Auto" : "Std.");
	}
}
