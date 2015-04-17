package com.tsa.puridiom.userprofile.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userprofile.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class UserProfileUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserProfileUpdate test = new UserProfileUpdate();
			Map incomingRequest = new HashMap();

			UserProfile userProfile = new UserProfile();
			userProfile.setMailId("kelli@tsagate.com");
			userProfile.setUserId("KELLI");
			userProfile.setOrganizationId("PURIDIOM");
			userProfile.setApprovalAmount(new BigDecimal(10500));
			userProfile.setBuyer("N");
			userProfile.setDepartment("PURCHASING");
			userProfile.setFaxNumber("717-691-5690");
			userProfile.setFirstName("Kelli");
			userProfile.setLastName("Knisely");
			userProfile.setLockLogin("N");
			userProfile.setMiddleInit("J");
			userProfile.setOwner("UPDATETEST");
			userProfile.setPhoneNumber("717-691-5691 ext. 130");
			userProfile.setReceiver("Y");
			userProfile.setRequisitioner("Y");
			userProfile.setShipToCode("SOUTH");
			userProfile.setUserPassword("NPOGL");
			userProfile.setWarrantAmount(new BigDecimal(2500));
			
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
			System.out.println("UserProfileUpdateTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}