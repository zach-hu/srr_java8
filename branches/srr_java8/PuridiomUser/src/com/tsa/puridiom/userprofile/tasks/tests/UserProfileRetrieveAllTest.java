package com.tsa.puridiom.userprofile.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userprofile.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class UserProfileRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserProfileRetrieveAll test = new UserProfileRetrieveAll();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("UserProfile_userId", "KELLI");
			incomingRequest.put("UserProfile_organizationId", "PURIDIOM");
			
			List list = (List) test.executeTask(incomingRequest);
			
			if (list != null) {
				System.out.println("Found " + list.size() + " record(s).");

				for (int i=0; i < list.size(); i++) {
					UserProfile userProfile = (UserProfile) list.get(i);
					if (userProfile == null) {
						System.out.println("UserProfile was null.");
					} else {
						System.out.println(userProfile.toString());
					}
				}
			} else {
				System.out.println("No records found.");
			}
			System.out.println(incomingRequest);
			System.out.println("UserProfileRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}