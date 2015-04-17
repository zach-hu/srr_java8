package com.tsa.puridiom.handlers.test.user;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import com.tsa.puridiom.usermanager.*;
import java.util.*;

public class SecurityGroupProfileUpdateHandlerTest {

	public SecurityGroupProfileUpdateHandlerTest()
	{
	}

	public static void main(String args[]) throws Exception
	{
		SecurityGroupProfileUpdateHandler testHandler = new SecurityGroupProfileUpdateHandler();

		try
		{
			HashMap testParameters = new HashMap();
			String	profileTypes[] = {"G", "G", "G", "G"};
			String	groupIds[] = {"TEST", "TEST", "TEST", "TEST"};
			String	profiles[] = {"REQ", "RFQ", "PO", "ADMIN"};
			String	flags[] = {"S", "S", "S", "C"};

			testParameters.put("organizationId", "puridiom");
			testParameters.put("userId", "KELLI");
			testParameters.put("successPage", "SUCCESS");
			testParameters.put("failurePage", "FAILURE");
			testParameters.put("SecurityGroup_groupId", groupIds[0]);
			testParameters.put("SecurityGroup_groupDescription", "TESTING");
			testParameters.put("SecurityProfile_groupId", groupIds);
			testParameters.put("SecurityProfile_profileType", profileTypes);
			testParameters.put("SecurityProfile_profile", profiles);
			testParameters.put("SecurityProfile_flags", flags);

			Map resultMap = (Map) testHandler.handleRequest(testParameters);
			UserRole userRole = (UserRole) resultMap.get("userRole");

			System.out.println("SecurityGroupProfileUpdateHandler TEST viewPage - " + resultMap.get("viewPage"));
			System.out.println("SecurityGroupProfileUpdateHandler TEST errorMsg - " + resultMap.get("errorMsg"));

			UserRole	role = UserRoleManager.getInstance().getUserRole("TEST", "PURIDIOM");

			System.out.println("SecurityGroupProfileUpdateHandler TEST userRole - " + userRole.toString());
			System.out.println("SecurityGroupProfileUpdateHandler TEST role - " + userRole.toString());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE!");
			e.printStackTrace();
		}

		System.out.println("TEST COMPLETE!");
	}

}