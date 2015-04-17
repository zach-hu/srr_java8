/*
 * Created on Oct 7, 2003 
 */
package com.tsagate.foundation.utility;


/**
 * @author renzo 
 */
public class FormatDateTest
{
	public static void main(String[] args)
	{
		String pattern = "dd/MM/yyyy";
		String tmp = Utility.getDateFormat(null, pattern);
		//system.out.println(tmp);
	}
}
