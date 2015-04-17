package com.tsagate.foundation.security.tasks.test;

import com.tsagate.foundation.security.tasks.*;
import java.util.*;

public class RetrieveOrganizationListTest {

	public RetrieveOrganizationListTest()
	{		
	}
	
	public static void main(String args[]) throws Exception 
	{
		RetrieveOrganizationList testTask = new RetrieveOrganizationList();
		
		try 
		{
			HashMap testParameters = new HashMap();
			List organizationList = (ArrayList) testTask.executeTask(testParameters);
			
			if (organizationList != null)
			{
				//system.out.println("SUCCESS!");
				
				Iterator iterator = organizationList.iterator();
				
				while (iterator.hasNext())
				{
					String	organization = (String) iterator.next();
					//system.out.println(organization);
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