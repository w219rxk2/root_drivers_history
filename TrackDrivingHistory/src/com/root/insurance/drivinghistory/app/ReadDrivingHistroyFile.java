package com.root.insurance.drivinghistory.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.root.insurance.drivinghistory.dto.Driver;
import com.root.insurance.drivinghistory.exception.CustomFileNotFoundExcepion;
import com.root.insurance.drivinghistory.exception.CustomIOException;
import com.root.insurance.drivinghistroy.utility.TrackDrivingHistoryUtil;

/**
 * This class is used as driver class(Application starts from this class).
 * 
 * @author Ravi Chandra Kolli
 */
public class ReadDrivingHistroyFile {
	
	/**
	 * This method is used to read and write the driving history.
	 * 
	 * @param inputFile
	 */
	public static void readAndWriteDriversHistory(BufferedReader inputFile) throws CustomIOException, CustomFileNotFoundExcepion 
	{
		// Creates a FileReader Object
		String line;
		TrackDrivingHistoryUtil trackDrivingHistoryUtil = new TrackDrivingHistoryUtil();
		
		//Read File Line By Line
		try {
			while ((line = inputFile.readLine()) != null) 
			{
			  trackDrivingHistoryUtil.toggleMode(line);
			}
		} catch (IOException e1) {
			throw new CustomIOException("error while reading the file.");
		}
		
		// After reading the input file print the required output file
		Driver[] driverHistoryList = trackDrivingHistoryUtil.printDriverHistoryFile();
		
		try(FileWriter fw = new FileWriter("src/Driving_History_Output.txt");BufferedWriter bw = new BufferedWriter(fw) ) {
			for(int i = driverHistoryList.length-1 ; i >= 0; i--) {
				if(driverHistoryList[i] != null) {
					bw.write(driverHistoryList[i].toString());
					bw.newLine();
				}
			}
		}catch(IOException e) {
			throw new CustomFileNotFoundExcepion("File is not found at the specified location");
		}
		
	}
	
	// Driver class
	public static void main(String[] args) throws CustomFileNotFoundExcepion {
		
		try(FileInputStream fileStream = new FileInputStream(args.length > 0 ? args[0] : "src/Driving_Data.txt")) 
		{
			BufferedReader bufferedStream = new BufferedReader(new InputStreamReader(fileStream));
			readAndWriteDriversHistory(bufferedStream);
		} 
		catch (IOException e) {
			throw new CustomFileNotFoundExcepion("File is not found at the specified location");
		}
	}

}
