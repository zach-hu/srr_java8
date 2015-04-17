package com.puridiom.service.budget;

public class BugetNotFound extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 9097920954297543921L;

	public String getMessage()
	{
		return "Budget Not Found";
	}

}
