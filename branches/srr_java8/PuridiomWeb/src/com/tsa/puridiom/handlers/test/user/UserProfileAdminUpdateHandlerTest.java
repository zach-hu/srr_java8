package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class UserProfileAdminUpdateHandlerTest {

	public UserProfileAdminUpdateHandlerTest()
	{		
	}
	
	public static void main(String args[]) throws Exception 
	{
		UserProfileAdminUpdateHandler testHandler = new UserProfileAdminUpdateHandler();
		
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
			testParameters.put("UserProfile_userPassword", password);
			testParameters.put("confirmUserPassword", password);
			testParameters.put("UserProfile_firstName", "FIRSTNAME2");
			testParameters.put("UserProfile_lastName", "LASTNAME2");
			testParameters.put("UserProfile_department", "IT DEPT2");
			testParameters.put("UserProfile_empid", "HT000001");
			
			testParameters.put("successPage", "MENU PAGE");
			testParameters.put("failurePage", "ERROR PAGE");
			testParameters.put("registrationFailurePage", "REGISTRATION PAGE");
			
			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			String	uid = (String) resultMap.get("userId");
			String	oid = (String) resultMap.get("organizationId");
			
			System.out.println("UserProfileAdminUpdateHandler TEST viewPage - " + resultMap.get("viewPage"));
			System.out.println("UserProfileAdminUpdateHandler TEST errorMsg - " + resultMap.get("errorMsg"));
			System.out.println("UserProfileAdminUpdateHandler TEST userId - " + uid);
			System.out.println("UserProfileAdminUpdateHandler TEST organizationId - " + oid);
			
			UserProfile	user = UserManager.getInstance().getUser(oid,uid);
			UserRole	role = UserManager.getInstance().getUserRole(oid,uid);
			
			System.out.println("UserProfileAdminUpdateHandler TEST user.getRoles() - " + user.getUserRoles().toString());
			System.out.println("UserProfileAdminUpdateHandler TEST role - " + role.toString());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
		}
		
		System.out.println("TEST COMPLETE!");
	}
	
}