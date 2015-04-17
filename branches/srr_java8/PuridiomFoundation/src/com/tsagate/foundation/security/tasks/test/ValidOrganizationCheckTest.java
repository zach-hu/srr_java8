package com.tsagate.foundation.security.tasks.test;

import com.tsagate.foundation.security.tasks.ValidOrganizationCheck;
import java.util.*;

public class ValidOrganizationCheckTest {

	public ValidOrganizationCheckTest()
	{
	}
	
	public static void main(String args[]) throws Exception 
	{
		try 
		{
			Map request = new HashMap();
			ValidOrganizationCheck testTask = new ValidOrganizationCheck();
			
			//system.out.println("The result of this test should be INVALID.");
			request.put("o", "INVALIDORGANIZATION");
			
			Boolean isValid = (Boolean) testTask.executeTask(request);
			
			if (isValid.booleanValue())
			{
				//system.out.println("IS VALID!");
			}
			else
			{
				//system.out.println("IS NOT VALID!");
			}
			
			//system.out.println("The result of this test should be VALID.");
			request.put("o", "PURIDIOM");
			
			isValid = (Boolean) testTask.executeTask(request);
			
			if (isValid.booleanValue())
			{
				//system.out.println("IS VALID!");
			}
			else
			{
				//system.out.println("IS NOT VALID!");
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