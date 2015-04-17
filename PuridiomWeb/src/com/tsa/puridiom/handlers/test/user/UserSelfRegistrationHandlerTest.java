package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class UserSelfRegistrationHandlerTest {

	public UserSelfRegistrationHandlerTest()
	{
	}

	public static void main(String args[]) throws Exception
	{
		UserSelfRegistrationHandler testHandler = new UserSelfRegistrationHandler();

		try
		{
			HashMap testParameters = new HashMap();
			String	mailId = "self.registration@tsatest.com";

			testParameters.put("companyName", "Registration Test Company, Inc.");
			testParameters.put("UserProfile_mailId", mailId);
			testParameters.put("UserProfile_firstName", "Firstname");
			testParameters.put("UserProfile_middleInit", "M");
			testParameters.put("UserProfile_lastName", "Lastname");
			testParameters.put("UserProfile_phoneNumber", "1-888-888-8880");
			testParameters.put("UserProfile_faxNumber", "1-888-888-8881");

			testParameters.put("successPage", "MENU PAGE");
			testParameters.put("failurePage", "ERROR PAGE");
			testParameters.put("registrationFailurePage", "REGISTRATION PAGE");

			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			String	uid = (String) resultMap.get("userId");
			String	oid = (String) resultMap.get("organizationId");

			System.out.println("UserSelfRegistrationHandler TEST viewPage - " + resultMap.get("viewPage"));
			System.out.println("UserSelfRegistrationHandler TEST errorMsg - " + resultMap.get("errorMsg"));
			System.out.println("UserSelfRegistrationHandler TEST userId - " + uid);
			System.out.println("UserSelfRegistrationHandler TEST organizationId - " + oid);

			UserProfile	user = UserManager.getInstance().getUser(oid,uid);
			UserRole	role = UserManager.getInstance().getUserRole(oid,uid);

			System.out.println("UserSelfRegistrationHandler TEST user.getRoles() - " + user.getUserRoles().toString());
			System.out.println("UserSelfRegistrationHandler TEST role - " + role.toString());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
		}

		System.out.println("TEST COMPLETE!");
	}

}