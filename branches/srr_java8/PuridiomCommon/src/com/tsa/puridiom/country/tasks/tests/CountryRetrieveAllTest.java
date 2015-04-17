package com.tsa.puridiom.country.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.country.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class CountryRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("qri06p");
			CountryRetrieveAll test = new CountryRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "qri06p");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List countryList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < countryList.size(); i++)
			{
				Country country = (Country) countryList.get(i);
				System.out.println("Country: " + country.toString());
			}
		
			System.out.println("CountryRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}