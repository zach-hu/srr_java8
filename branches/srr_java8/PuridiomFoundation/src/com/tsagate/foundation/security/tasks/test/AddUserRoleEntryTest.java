package com.tsagate.foundation.security.tasks.test;

import com.tsagate.foundation.security.tasks.*;
import java.util.*;

public class AddUserRoleEntryTest {

	public AddUserRoleEntryTest()
	{
	}
	
	public static void main(String args[]) throws Exception 
	{
		AddUserRoleEntry testTask = new AddUserRoleEntry();
		
		try 
		{
			String	oValues[] = {"PURIDIOM"};
			String	ridValues[] = {"NEWUSERROLE-TEST"};
			String	descriptionValues[] = {"This is only a test!"};
			Map		accessProperties = new HashMap();
			accessProperties.put("REQ", "S");
			accessProperties.put("REQBROWSE", "S");
			accessProperties.put("PO", "C");
			accessProperties.put("POBROWSE", "M");
			accessProperties.put("ADMIN", "");
			Map testParameters = new HashMap();
			
			testParameters.put("oValues", oValues); 
			testParameters.put("ridValues", ridValues); 
			testParameters.put("descriptionValues", descriptionValues);
			testParameters.put("accessPropertyValues", accessProperties);
			
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