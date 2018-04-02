package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
			ts.filterMode(driverData);
		}
		
		String[] output = {"Dan: 39 miles @ 47 mph", "Alex: 42 miles @ 34 mph", "Bob: 0 miles"};
		
		String[] deliveryFile = ts.printDriverHistoryFile();
		
		assertEquals(output.length, deliveryFile.length);
		
		for(int i =0; i < deliveryFile.length; i++) {
			String actualString = output[i];
			String extectedString = deliveryFile[i];
			
			String[] actualSplit = actualString.split(" ");
			String[] extectedSplit = extectedString.split(" ");
			
			assertEquals(actualSplit[1], extectedSplit[1]);
			if(actualSplit.length > 3) {
				assertEquals(actualSplit[4], extectedSplit[4]);
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
			ts.filterMode(content);
		}
		
		int size = ts.getDrivers().size();
		
		assertEquals(3, size);
	}
	
}
