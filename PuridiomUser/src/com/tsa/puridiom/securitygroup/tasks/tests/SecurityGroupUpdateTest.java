package com.tsa.puridiom.securitygroup.tasks.tests;

import com.tsa.puridiom.entity.SecurityGroup;
import com.tsa.puridiom.securitygroup.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class SecurityGroupUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			SecurityGroupUpdate test = new SecurityGroupUpdate();
			Map incomingRequest = new HashMap();

			SecurityGroup securityGroup = new SecurityGroup();
			securityGroup.setDateEntered(new Date());
			securityGroup.setDateExpires(null);
			securityGroup.setGroupDescription("Java Unit Test Update");
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
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("SecurityGroupUpdateTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}