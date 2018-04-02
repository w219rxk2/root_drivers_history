package com.root.insurance.drivinghistroy.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.root.insurance.drivinghistory.dto.Driver;
import com.root.insurance.drivinghistory.dto.DriverKey;
import com.root.insurance.drivinghistory.dto.Schedule;

/**
 * This class contains utility methods required for Tracking Driving History.
 * 
 * @author Ravi Chandra Kolli
 */
public class TrackDrivingHistoryUtil
{
	private Map<DriverKey,Driver> drivers = new HashMap<>();
	
	/**
	 * This method reads the line and determine whether it is a new driver or trip mode.
	 * If either of the cases doesn't match it will throw an exception mentioning particular case and continue
	 * 
	 * @param character stream of lines.
	 */
	public void toggleMode(String content)
	{
		String[] attributes = content.split("\\s+");
		
		// using switch case since it is switching up-on single value (Driver or Trip)
		switch(attributes[0]) {
			case "Driver" : 
				if(attributes.length == 2) {
					// save new drivers
					saveNewDriver(attributes[1]);
				}
			case "Trip" :
				if(attributes.length == 5) {
					// handle trips
					handleNewTrip(attributes);
				}
		}
	}
	
	/**
	 * This method is used to save data to the MAP object as we are not using database.
	 * 
	 * For this process it will persist the data in java.util.Map object
	 * 
	 * @param name
	 */
	private void saveNewDriver(String name) {
		Driver driver = new Driver();
		driver.setName(name);
		DriverKey dk = new DriverKey(name);
		
		drivers.put(dk, driver);
	}
	
	/**
	 * This method is used handle the new trips made by the drivers and 
	 * add them to the schedule Map for the particular Driver Map
	 * 
	 * @param driverAttributes
	 */
	private void handleNewTrip(String[] driverAttributes) {
		String name = driverAttributes[1];
		DriverKey driverKey = new DriverKey(name);
		
		if(drivers.containsKey(driverKey))
		{
			Map<Schedule,Double> scheduleMap;
			Driver driver = drivers.get(driverKey);
			Schedule sc = new Schedule(driverAttributes[2], driverAttributes[3]);
			Double miles  = Double.valueOf(driverAttributes[4]);
			
			if(driver.getSchedule() != null) {
				scheduleMap = driver.getSchedule();
				if(scheduleMap.containsKey(sc)) {
					// throw error driver cannot claim for the same time again.
				}else {
					scheduleMap.put(sc, miles);
				}
			}else {
				scheduleMap = new HashMap<>();
				scheduleMap.put(sc, miles);
				driver.setSchedule(scheduleMap);
			}
		} else {
			// throw error and continue
		}
	}
	
	/**
	 * This method is used to print Driver history output file and calculates the hours and miles for each driver
	 * 
	 * @param driverAttributes
	 */
	public String[] printDriverHistoryFile() {
		
		int size = drivers.size();
		String[] outputFileArray = new String[size];
		int outputFileInc = 0;
		
		Set<DriverKey> driverKeys = drivers.keySet();
		
		for(DriverKey dk : driverKeys) {
			
			double totalMiles = 0;
			double totalHours    = 0;
			Driver driver = drivers.get(dk);
			
			if(driver.getSchedule() != null) {
				
				Map<Schedule, Double> schedules = driver.getSchedule();
				Set<Entry<Schedule, Double>> sheduleEntrys = schedules.entrySet();
				for(Entry<Schedule, Double> entry : sheduleEntrys ) {
					
					Schedule schedule = entry.getKey();
					
					String[] startTimeSplit = schedule.getStartTime().split(":");
					String[] endTimeSplit = schedule.getEndTime().split(":");
					
					if(startTimeSplit.length == 2 && endTimeSplit.length == 2) {
						
						int startMinutes = (Integer.valueOf(startTimeSplit[0])*60) + Integer.valueOf(startTimeSplit[1]);
						
						int endMinutes = (Integer.valueOf(endTimeSplit[0])*60) + Integer.valueOf(endTimeSplit[1]);
						
						if(endMinutes > startMinutes) {	
							
							int t = endMinutes - startMinutes;
							double hours  = Double.valueOf(t)/ 60;
							
							totalHours = totalHours + hours;
							totalMiles = totalMiles + entry.getValue();
						}else {
							// not a valid record since end time is not after the start time.
						}
					}else {
						// not a valid record since time is not in correct format.
					}
				}
				
			}
			
			long speed = Math.round(totalMiles/totalHours);
			
			if(speed >= 5 && speed <= 100) {
				// Alex: 42 miles @ 34 mph
				StringBuilder outputString  = new StringBuilder();
				outputString.append(driver.getName());
				outputString.append(": ");
				outputString.append(Math.round(totalMiles));
				outputString.append(" miles");
				if(totalHours != 0) {
					outputString.append(" @ ");
					outputString.append(Math.round(totalMiles/totalHours));
					outputString.append(" mph");
				}
				
				outputFileArray[outputFileInc] = outputString.toString();
				outputFileInc++;
			}
		}
		return outputFileArray;
	}
	
	/**
	 * only for testing purpose
	 * @return map object
	 */
	public Map<DriverKey, Driver> getDrivers() {
		return drivers;
	}
	
}
