package com.tsagate.foundation.utility.test;

import com.tsagate.foundation.utility.Dates;
import java.sql.Date;

/**
 * @author kelli
 */
public class DatesTest
{
	public DatesTest ()
	{
		super();
	}
	
	public static void main(String args[])
	{
		DatesTest test = new DatesTest();
		
		try
		{
			if (!test.getDateTest())
			{
				//system.out.println("getDate FAILED.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//system.out.println("COMPLETE");
	}
	public boolean getDateTest()
	{
		boolean success = false;
		
		try
		{
			String sdate = "2003-11-14";
			Date sqlDate = Dates.getDate(sdate);
				
			//system.out.println("getDate returned: " + sqlDate.toString());
			success = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			success = false;
		}
		finally
		{
			return success;
		}
	}
}
