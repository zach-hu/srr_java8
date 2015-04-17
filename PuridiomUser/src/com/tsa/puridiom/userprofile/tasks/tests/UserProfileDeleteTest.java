package com.tsa.puridiom.userprofile.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userprofile.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class UserProfileDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserProfileDeleteById test = new UserProfileDeleteById();
			Map incomingRequest = new HashMap();

			UserProfile userProfile = new UserProfile();
			userProfile.setMailId("kelli@tsagate.com");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("userProfile", userProfile);

			userProfile = (UserProfile) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("UserProfileDeleteByIdTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}