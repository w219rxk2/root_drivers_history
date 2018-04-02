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
}
