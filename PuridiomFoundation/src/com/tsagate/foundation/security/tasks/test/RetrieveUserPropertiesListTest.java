package com.tsagate.foundation.security.tasks.test;

import com.tsagate.foundation.security.tasks.*;
import java.util.*;

public class RetrieveUserPropertiesListTest {

	public RetrieveUserPropertiesListTest()
	{		
	}
	
	public static void main(String args[]) throws Exception 
	{
		RetrieveUserPropertiesList testTask = new RetrieveUserPropertiesList();
		
		try 
		{
			HashMap testParameters = new HashMap();
			HashMap filterCriteria = new HashMap();
			
			filterCriteria.put("firstName", "T*");
			
			testParameters.put("o", "PURIDIOM");
			testParameters.put("filterCriteria",filterCriteria);
			
			List userPropertiesList = (ArrayList) testTask.executeTask(testParameters);
			
			if (userPropertiesList != null)
			{
				//system.out.println("SUCCESS!");
				
				Iterator iterator = userPropertiesList.iterator();
				
				while (iterator.hasNext())
				{
					HashMap userProperties = (HashMap) iterator.next();
					//system.out.println("User Properties - " + userProperties.toString());
				}
			}
		}
		catch(Exception e)
		{
			//system.out.println("FAILURE: " + e.getMessage());
		}
		
		//system.out.println("TEST COMPLETE!");
	}
	
}