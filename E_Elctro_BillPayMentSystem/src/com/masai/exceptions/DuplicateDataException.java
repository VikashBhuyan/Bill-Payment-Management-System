package com.masai.exceptions;

public class DuplicateDataException extends Exception{

	public DuplicateDataException(){
		super();
	}
	public DuplicateDataException(String msg)
	{
		super(msg);
	}
}
