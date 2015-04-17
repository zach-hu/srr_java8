package com.tsa.puridiom.userprofile.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userprofile.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class UserProfileAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserProfileAdd test = new UserProfileAdd();
			Map incomingRequest = new HashMap();

			UserProfile userProfile = new UserProfile();
			userProfile.setMailId("kelli@tsagate.com");
			userProfile.setUserId("KELLI");
			userProfile.setOrganizationId("PURIDIOM");
			userProfile.setApprovalAmount(new BigDecimal(10000));
			userProfile.setBuyer("Y");
//			userProfile.setDateEntered(new Date());
			userProfile.setDepartment("IT");
			userProfile.setFaxNumber("717-691-5690");
			userProfile.setFirstName("Kelli");
			userProfile.setLastName("Knisely");
			userProfile.setLockLogin("N");
			userProfile.setMiddleInit("J");
			userProfile.setOwner("PURIDIOMTEST");
			userProfile.setPhoneNumber("717-691-5691 ext. 130");
			userProfile.setReceiver("Y");
			userProfile.setRequisitioner("Y");
			userProfile.setShipToCode("CENTRAL");
			userProfile.setUserPassword("NPOGL");
			userProfile.setWarrantAmount(new BigDecimal(5000));
			
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
			System.out.println("UserProfileAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}