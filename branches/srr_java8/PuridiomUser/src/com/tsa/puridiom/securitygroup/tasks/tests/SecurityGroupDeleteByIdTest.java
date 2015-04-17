package com.tsa.puridiom.securitygroup.tasks.tests;

import com.tsa.puridiom.entity.SecurityGroup;
import com.tsa.puridiom.securitygroup.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class SecurityGroupDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			SecurityGroupDeleteById test = new SecurityGroupDeleteById();
			Map incomingRequest = new HashMap();
			 
			 dbs.startTransaction();
			 
			 incomingRequest.put("dbsession", dbs);
			 incomingRequest.put("organizationId", "PURIDIOM");
			 incomingRequest.put("userId", "KELLI");
			 incomingRequest.put("SecurityGroup_groupId", "TEST");

			 test.executeTask(incomingRequest);
			 
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("SecurityGroupDeleteByIdTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}