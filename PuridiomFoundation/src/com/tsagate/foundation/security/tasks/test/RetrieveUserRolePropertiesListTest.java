package com.tsagate.foundation.security.tasks.test;

import com.tsagate.foundation.security.tasks.*;
import java.util.*;

public class RetrieveUserRolePropertiesListTest {

	public RetrieveUserRolePropertiesListTest()
	{		
	}
	
	public static void main(String args[]) throws Exception 
	{
		RetrieveUserRolePropertiesList testTask = new RetrieveUserRolePropertiesList();
		
		try 
		{
			HashMap testParameters = new HashMap();
			HashMap filterCriteria = new HashMap();
			
			//filterCriteria.put("description", "*test*");
			
			testParameters.put("o", "PURIDIOM");
			testParameters.put("filterCriteria",filterCriteria);
			
			List rolePropertiesList = (ArrayList) testTask.executeTask(testParameters);
			
			if (rolePropertiesList != null)
			{
				//system.out.println("SUCCESS!");
				
				Iterator iterator = rolePropertiesList.iterator();
				
				while (iterator.hasNext())
				{
					HashMap roleProperties = (HashMap) iterator.next();
					//system.out.println("Role Properties - " + roleProperties.toString());
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