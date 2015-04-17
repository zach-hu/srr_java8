package com.tsa.puridiom.userprofile.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userprofile.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class UserProfileGetDaysAfterPasswordSetTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			UserProfileDaysAfterPasswordSet test = new UserProfileDaysAfterPasswordSet();
			Map incomingRequest = new HashMap();
			UserProfile userProfile = new UserProfile();
			userProfile.setMailId("kelli@tsagate.com");
			userProfile.setOrganizationId("PURIDIOM");
			userProfile.setUserId("KELLI");
			userProfile.setPassChanged("2001-01-01");
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userProfile", userProfile);
			
			Integer daysAfter = (Integer) test.executeTask(incomingRequest);
			if (daysAfter == null) {
				System.out.println("daysAfter returned null.");
			} else {
				System.out.println("daysAfter = "+ String.valueOf(daysAfter));
			}
			System.out.println(incomingRequest);
			System.out.println("UserProfileDaysAfterPasswordSetTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}