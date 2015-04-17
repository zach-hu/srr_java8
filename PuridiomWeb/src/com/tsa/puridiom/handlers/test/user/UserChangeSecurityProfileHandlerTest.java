package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class UserChangeSecurityProfileHandlerTest {

	public UserChangeSecurityProfileHandlerTest()
	{		
	}
	
	public static void main(String args[]) throws Exception 
	{
		UserChangeSecurityProfileHandler testHandler = new UserChangeSecurityProfileHandler();
		
		try 
		{
			HashMap testParameters = new HashMap();
			String	userId = "KELLI";
			
			testParameters.put("organizationId", "PURIDIOM");
			testParameters.put("userId", userId);
			testParameters.put("UserProfile_userId", userId);
			//testParameters.put("securityAnswer", "");
			testParameters.put("newSecurityQuestion", "What is your mother's maiden name?");
			testParameters.put("newSecurityAnswer", "Maddy");
			testParameters.put("successPage", "MENU PAGE");
			testParameters.put("failurePage", "ERROR PAGE");
			testParameters.put("resetFailurePage", "RESET SECURITY PAGE");
			
			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			String	uid = (String) resultMap.get("userId");
			String	oid = (String) resultMap.get("organizationId");
			
			System.out.println("UserChangeSecurityProfileHandler TEST - " + resultMap.get("viewPage"));
			System.out.println("UserChangeSecurityProfileHandler TEST errorMsg - " + resultMap.get("errorMsg"));
			
			UserProfile	user = UserManager.getInstance().getUser(oid,uid);
			
			System.out.println(user.getSecurityQuestion() + " = " + user.getSecurityAnswer());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
		}
		
		System.out.println("TEST COMPLETE!");
	}
	
}