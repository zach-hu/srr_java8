package com.tsa.puridiom.hostuser.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.hostuser.tasks.*;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class HostUserAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			HostUserAdd test = new HostUserAdd();
			Map incomingRequest = new HashMap();
		
			HostUser hostUser = new HostUser();
		
			hostUser.setMailId("mailIdTest@testhilton.com");
			hostUser.setOrganizationId("TEST");
			hostUser.setUserId("TESTUSER");
			
			incomingRequest.put("hostUser", hostUser);
		
			hostUser = (HostUser) test.executeTask(incomingRequest);
			if (test.getStatus() == Status.SUCCEEDED) {
				System.out.println("HostUserAddTest SUCCESS");
			}
		
			System.out.println(incomingRequest);
			System.out.println("HostUserAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}