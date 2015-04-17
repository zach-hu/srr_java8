package com.tsa.puridiom.securitygroup.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.securitygroup.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class SecurityGroupRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			SecurityGroupRetrieveAll test = new SecurityGroupRetrieveAll();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			
			List list = (List) test.executeTask(incomingRequest);
			
			if (list != null) {
				System.out.println("Found " + list.size() + " record(s).");

				for (int i=0; i < list.size(); i++) {
					SecurityGroup securityGroup = (SecurityGroup) list.get(i);
					if (securityGroup == null) {
						System.out.println("SecurityGroup entity was null.");
					} else {
						System.out.println(securityGroup.toString());
					}
				}
			} else {
				System.out.println("No records found.");
			}
			System.out.println(incomingRequest);
			System.out.println("SecurityGroupRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}