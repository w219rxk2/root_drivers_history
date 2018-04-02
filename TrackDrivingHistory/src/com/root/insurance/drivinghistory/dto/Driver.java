package com.root.insurance.drivinghistory.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Data transition object used to store data of a particular Driver.
 * 
 * @author Ravi Chandra Kolli
 */
public class Driver implements Serializable
{
	private static final long serialVersionUID = 8360914566994418318L;
	
	private String name;
	private Map<Schedule,Double> schedule;
	private double totalHours;
	private double totalMiles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Schedule, Double> getSchedule() {
		return schedule;
	}
	public void setSchedule(Map<Schedule, Double> schedule) {
		this.schedule = schedule;
	}
	public double getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}
	public double getTotalMiles() {
		return totalMiles;
	}
	public void setTotalMiles(double totalMiles) {
		this.totalMiles = totalMiles;
	}
	
	@Override
	public String toString() {
		
		StringBuilder outputString  = new StringBuilder();
		outputString.append(this.name);
		outputString.append(": ");
		outputString.append(Math.round(totalMiles));
		outputString.append(" miles");
		if(totalHours != 0) {
			outputString.append(" @ ");
			outputString.append(Math.round(totalMiles/totalHours));
			outputString.append(" mph");
		}
		return outputString.toString();
	}
}
