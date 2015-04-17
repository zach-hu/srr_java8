package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class UserLoginHandlerTest {

	public UserLoginHandlerTest()
	{
	}

	public static void main(String args[]) throws Exception
	{
		UserLoginHandler testHandler = new UserLoginHandler();

		try
		{
			HashMap testParameters = new HashMap();
			String	mailId = "self.registration@tsatest.com";
			String	password = "pswd123";

//			testParameters.put("organizationId", "puridiom");
			testParameters.put("mailId", mailId);
			testParameters.put("password", password);
			testParameters.put("successPage", "MENU PAGE");
			testParameters.put("failurePage", "ERROR PAGE");
			testParameters.put("loginFailurePage", "LOGIN PAGE");
			testParameters.put("passwordResetPage", "RESET PASSWORD PAGE");

			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			String	uid = (String) resultMap.get("userId");
			String	oid = (String) resultMap.get("organizationId");

			System.out.println("UserLoginHandler TEST viewPage - " + resultMap.get("viewPage"));
			System.out.println("UserLoginHandler TEST errorMsg - " + resultMap.get("errorMsg"));
			System.out.println("UserLoginHandler TEST userId - " + uid);
			System.out.println("UserLoginHandler TEST organizationId - " + oid);

			UserProfile	user = UserManager.getInstance().getUser(oid,uid);
			UserRole	role = UserManager.getInstance().getUserRole(oid,uid);

			System.out.println("UserLoginHandler TEST user.getRoles() - " + user.getUserRoles().toString());
			System.out.println("UserLoginHandler TEST role - " + role.toString());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
		}

		System.out.println("TEST COMPLETE!");
	}

}