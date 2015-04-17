package com.tsa.puridiom.securitygroup.tasks.tests;

import com.tsa.puridiom.entity.SecurityGroup;
import com.tsa.puridiom.securitygroup.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class SecurityGroupAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			SecurityGroupAdd test = new SecurityGroupAdd();
			Map incomingRequest = new HashMap();

			SecurityGroup securityGroup = new SecurityGroup();
			securityGroup.setDateEntered(new Date());
			securityGroup.setDateExpires(null);
			securityGroup.setGroupDescription("Java Unit Test");
			securityGroup.setGroupId("TEST");
			securityGroup.setOwner("JAVATEST");
			securityGroup.setStatus("02");
			 
			 dbs.startTransaction();
			 
			 incomingRequest.put("dbsession", dbs);
			 incomingRequest.put("organizationId", "PURIDIOM");
			 incomingRequest.put("userId", "KELLI");
			 incomingRequest.put("securityGroup", securityGroup);

			 securityGroup = (SecurityGroup) test.executeTask(incomingRequest);
			 
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("COMMIT");
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("SecurityGroupAddTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}