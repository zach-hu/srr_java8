package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class UserChangeSignaturePasswordHandlerTest {

	public UserChangeSignaturePasswordHandlerTest()
	{
	}

	public static void main(String args[]) throws Exception
	{
		UserChangeSignaturePasswordHandler testHandler = new UserChangeSignaturePasswordHandler();

		try
		{
			HashMap testParameters = new HashMap();
			String	userId = "KELLI";

			testParameters.put("organizationId", "puridiom");
			testParameters.put("userId", userId);
			testParameters.put("UserProfile_userId", userId);
			testParameters.put("password", "PURIDIOM");
			testParameters.put("newPassword", "KELLI");
			testParameters.put("confirmPassword", "KELLI");
			testParameters.put("successPage", "MENU PAGE");
			testParameters.put("failurePage", "ERROR PAGE");
			testParameters.put("resetFailurePage", "RESET SIGNATURE PASSWORD PAGE");

			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			String	uid = (String) resultMap.get("userId");
			String	oid = (String) resultMap.get("organizationId");

			System.out.println("UserChangePasswordHandler TEST viewPage - " + resultMap.get("viewPage"));
			System.out.println("UserChangePasswordHandler TEST errorMsg - " + resultMap.get("errorMsg"));
			System.out.println("UserChangePasswordHandler TEST userId - " + uid);
			System.out.println("UserChangePasswordHandler TEST organizationId - " + oid);

			UserProfile	user = UserManager.getInstance().getUser(oid,uid);

			System.out.println("UserChangeSignaturePasswordHandler TEST user.getSignaturePassword() - " + user.getSignaturePassword());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE: " + e.getMessage());
		}

		System.out.println("TEST COMPLETE!");
	}

}