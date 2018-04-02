package com.root.insurance.drivinghistory.dto;

/**
 * Data transition object used to identify particular Driver
 * 
 * @author Ravi Chandra Kolli
 */
public class DriverKey {
	private String name;
	
	public DriverKey(String name) {
	  this.name = name;
	}
	
	/**
	 * Since this is used identify the driver we need to override the equals method
	 * to avoid multiple records for same driver
	 */
	@Override
    public boolean equals(Object obj)
	{
       if(obj == null) 
              return false;
       
       if(this.getClass() != obj.getClass()) 
              return false;

       DriverKey dk = (DriverKey)obj;
       
       return ((dk.name == this.name || dk.name.equals(this.name)));        
    }
    
	/**
	 * hash code for for storing driver data
	 */
    @Override
    public int hashCode()
    {
       int hash=(this.name == null ? 0 : this.name.hashCode());
       return hash;      
    }

	public String getName() {
	   return name;
	}
}
