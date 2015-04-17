package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class UserProfileAdminDeleteHandlerTest {

	public UserProfileAdminDeleteHandlerTest()
	{		
	}
	
	public static void main(String args[]) throws Exception 
	{
		UserProfileDeleteHandler testHandler = new UserProfileDeleteHandler();
		
		try 
		{
			HashMap testParameters = new HashMap();
			String	mailId = "admin.addnewuser@tsatest.com";
			String	password = "pswd123";
			
			testParameters.put("organizationId", "PURIDIOM");
			testParameters.put("userId", "KELLI");
			testParameters.put("UserProfile_organizationId", "PURIDIOM");
			testParameters.put("UserProfile_mailId", mailId);
			testParameters.put("UserProfile_userId", "H-TESTUSER2");
			
			testParameters.put("successPage", "MENU PAGE");
			testParameters.put("failurePage", "ERROR PAGE");
			
			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			String	uid = (String) resultMap.get("userId");
			String	oid = (String) resultMap.get("organizationId");
			
			System.out.println("UserProfileDeleteHandler TEST viewPage - " + resultMap.get("viewPage"));
			System.out.println("UserProfileDeleteHandler TEST errorMsg - " + resultMap.get("errorMsg"));
			System.out.println("UserProfileDeleteHandler TEST userId - " + uid);
			System.out.println("UserProfileDeleteHandler TEST organizationId - " + oid);
			
			UserProfile	user = UserManager.getInstance().getUser(oid,uid);
			UserRole	role = UserManager.getInstance().getUserRole(oid,uid);
			
			System.out.println("UserProfileDeleteHandler TEST user.getRoles() - " + user.getUserRoles().toString());
			System.out.println("UserProfileDeleteHandler TEST role - " + role.toString());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
		}
		
		System.out.println("TEST COMPLETE!");
	}
	
}