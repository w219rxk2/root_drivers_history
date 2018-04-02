package com.root.insurance.drivinghistory.dto;

import java.io.Serializable;

/**
 * Data transition object used to identify particular Schedule
 * 
 * @author Ravi Chandra Kolli
 */
public class Schedule implements Serializable {
	
	private static final long serialVersionUID = -8238862849119051885L;
	
	private String startTime;
	private String endTime;
	
	public Schedule(String startTime, String endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Since this is used identify the drivers schedule we need to override the equals method
	 * to avoid multiple records for same schedule
	 */
	@Override
    public boolean equals(Object obj)
	{
           if(obj == null) 
                  return false;
           
           if(this.getClass() != obj.getClass()) 
                  return false;
    
           Schedule sc = (Schedule)obj;
           
           return ((sc.startTime == this.startTime || sc.startTime.equals(this.startTime))
                               && (sc.endTime == this.endTime || sc.endTime.equals(this.endTime)));        
    }
         
	/**
	 * hash code for for storing particular schedule
	 */
    @Override
    public int hashCode()
    {
           int hash=(this.startTime == null ? 0 : this.startTime.hashCode() ) +
                        (this.endTime == null ? 0: this.endTime.hashCode());
           return hash;      
    }

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
}
