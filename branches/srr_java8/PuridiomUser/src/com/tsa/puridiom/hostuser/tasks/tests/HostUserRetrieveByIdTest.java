package com.tsa.puridiom.hostuser.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.hostuser.tasks.*;
import java.util.*;

public class HostUserRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			HostUserRetrieveById test = new HostUserRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("HostUser_mailId", "kelli@tsagate.com");
		
			HostUser hostUser = (HostUser) test.executeTask(incomingRequest);
		
			System.out.println("Task Status: " + test.getStatus());
			System.out.println("HostUser: " + hostUser.toString());
			System.out.println("HostUserRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}