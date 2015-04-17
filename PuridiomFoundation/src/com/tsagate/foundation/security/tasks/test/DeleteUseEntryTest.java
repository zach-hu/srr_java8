package com.tsagate.foundation.security.tasks.test;

import com.tsagate.foundation.security.tasks.*;
import java.util.*;

public class DeleteUseEntryTest {

	public DeleteUseEntryTest()
	{
	}
	
	public static void main(String args[]) throws Exception 
	{
		DeleteUserEntry testTask = new DeleteUserEntry();
		
		try 
		{
			String	o = "PURIDIOM";
			String	uid = "MIKE";
			Map testParameters = new HashMap();
			
			testParameters.put("organizationId", o);
			testParameters.put("o", o); 
			testParameters.put("User_userId", uid); 
			
			Boolean success = (Boolean)  testTask.executeTask(testParameters);
			
			if (success.booleanValue())
			{
				//system.out.println("SUCCESS!");
			}
		}
		catch(Exception e)
		{
			//system.out.println("FAILURE: " + e.getMessage());
			e.printStackTrace();
		}
		
		//system.out.println("TEST COMPLETE!");
	}
	
}