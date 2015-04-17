package com.tsa.puridiom.userprofile.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userprofile.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class UserProfileRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserProfileRetrieveByMailId test = new UserProfileRetrieveByMailId();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("UserProfile_mailId", "kelli@tsagate.com");

			UserProfile userProfile = (UserProfile) test.executeTask(incomingRequest);
			if (userProfile == null) {
				System.out.println("Record not found.");
			} else {
				System.out.println(userProfile.toString());
			}
			System.out.println(incomingRequest);
			System.out.println("UserProfileRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}