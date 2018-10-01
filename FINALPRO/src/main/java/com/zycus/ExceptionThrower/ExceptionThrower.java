package com.zycus.ExceptionThrower;

import com.zycus.ErrorHandler.CustomException;

public class ExceptionThrower {
	
	public void throwGeneralException() throws Exception
	{
		Exception e=new Exception("Error from general exception");
		throw e;
	}

	
	public void throwCustomException() throws CustomException
	{
		CustomException e=new CustomException();
		e.setCode(10);
		e.setMessage("Error from Custom exception");
		
		throw e;
	}
	
	public void throwNullPointerException()
	{
		String name;
		name=null;
		name=null+name;
		
	}

}
