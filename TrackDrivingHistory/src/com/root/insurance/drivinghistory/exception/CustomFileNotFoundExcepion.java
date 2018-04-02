package com.root.insurance.drivinghistory.exception;

import java.io.FileNotFoundException;

public class CustomFileNotFoundExcepion extends FileNotFoundException {

	private static final long serialVersionUID = -5584374085379328261L;
	
	public CustomFileNotFoundExcepion(String s) {
		super(s);
	}
}
