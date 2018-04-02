package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.root.insurance.drivinghistory.dto.Driver;
import com.root.insurance.drivinghistory.exception.CustomDriverInvalidException;
import com.root.insurance.drivinghistroy.utility.TrackDrivingHistoryUtil;

class TrackDrivingHistoryUtilTest {
	
	/**
	 * Tests com.root.insurance.drivinghistroy.utility.TrackDrivingHistoryUtil.printDriverHistoryFile()
	 * @throws CustomDriverInvalidException 
	 */
	@Test
	public void testPrintDriverHistoryFile() throws CustomDriverInvalidException {
		
		String[] input  = {"Driver Dan", "Driver Alex", "Driver Bob",	"Trip Dan 07:15 07:45 17.3", 
				"Trip Dan 06:12 06:32 21.8", "Trip Alex 12:01 13:16 42.0"};
		
		TrackDrivingHistoryUtil ts = new TrackDrivingHistoryUtil();
		
		for(String driverData : input) {
			ts.toggleMode(driverData);
		}
		
		String[] output = {"Bob: 0 miles",  "Dan: 39 miles @ 47 mph", "Alex: 42 miles @ 34 mph" };
		
		Driver[] driversFile = ts.printDriverHistoryFile();
		
		assertEquals(output.length, driversFile.length);
		
		for(int i =driversFile.length -1; i >= 0; i--) {
			String actualString = output[i];
			Driver driver = driversFile[i];
			
			String[] actualSplit = actualString.split(" ");
			
			assertEquals(actualSplit[1], String.valueOf(Math.round(driver.getTotalMiles())));
			if(actualSplit.length > 3) {
				assertEquals(actualSplit[4], String.valueOf(Math.round(driver.getTotalMiles()/driver.getTotalHours())));
			}
		}
	}
	
	/**
	 * tests com.root.insurance.drivinghistroy.utility.TrackDrivingHistoryUtil.filterMode(String)
	 * @throws CustomDriverInvalidException 
	 */
	@Test
	public void testFilterMode() throws CustomDriverInvalidException {
		
		String[] driverData  = {"Driver Dan", "Driver Alex", "Driver Bob",	"Trip Dan 07:15 07:45 17.3", 
				"Trip Dan 06:12 06:32 21.8", "Trip Alex 12:01 13:16 42.0"};
		
		TrackDrivingHistoryUtil ts = new TrackDrivingHistoryUtil();
		
		for(String content : driverData) {
			ts.toggleMode(content);
		}
		
		int size = ts.getDrivers().size();
		
		assertEquals(3, size);
	}
	
}
