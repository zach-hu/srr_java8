package com.tsa.puridiom.userregistration.tasks.tests;

import com.tsa.puridiom.userregistration.tasks.*;
import java.util.*;

public class GenerateUserIdFromNameTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			GenerateUserIdFromName test = new GenerateUserIdFromName();
			Map incomingRequest = new HashMap();
			String firstName = "Kelli";
			String lastName = "Knisely";

			incomingRequest.put("UserProfile_firstName", firstName);
			incomingRequest.put("UserProfile_lastName", lastName);

			String userId = (String) test.executeTask(incomingRequest);
			if (userId == null) {
				System.out.println("userId returned null for " + firstName + " " + lastName);
			} else {
				System.out.println("userId = " + userId + " [for " + firstName + " " + lastName + "]");
			}

			firstName = " j";
			lastName = "Mc-Daniel";

			incomingRequest.put("UserProfile_firstName", firstName);
			incomingRequest.put("UserProfile_lastName", lastName);

			userId = (String) test.executeTask(incomingRequest);
			if (userId == null) {
				System.out.println("userId returned null for " + firstName + " " + lastName);
			} else {
				System.out.println("userId = " + userId + " [for " + firstName + " " + lastName + "]");
			}

			System.out.println("GenerateOrgIdFromCompanyNameTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}