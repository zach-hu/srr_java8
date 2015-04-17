package com.tsa.puridiom.usergrouprel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.usergrouprel.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class UserGroupRelRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserGroupRelRetrieveBy test = new UserGroupRelRetrieveBy();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("UserGroupRel_userId", "KELLI");
			
			List list = (List) test.executeTask(incomingRequest);
			
			if (list != null) {
				System.out.println("Found " + list.size() + " record(s).");

				for (int i=0; i < list.size(); i++) {
					UserGroupRel userGroupRel = (UserGroupRel) list.get(i);
					if (userGroupRel == null) {
						System.out.println("UserGroupRel entity was null.");
					} else {
						System.out.println(userGroupRel.toString());
					}
				}
			} else {
				System.out.println("No records found.");
			}
			System.out.println(incomingRequest);
			System.out.println("UserGroupRelRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}