package com.tsa.puridiom.usergrouprel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.usergrouprel.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class UserGroupRelRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserGroupRelRetrieveById test = new UserGroupRelRetrieveById();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("UserGroupRel_groupId", "REQUISITIONER");
			incomingRequest.put("UserGroupRel_userId", "KELLI");

			UserGroupRel userGroupRel = (UserGroupRel) test.executeTask(incomingRequest);
			if (userGroupRel == null) {
				System.out.println("Record not found.");
			} else {
				System.out.println(userGroupRel.toString());
			}
			System.out.println(incomingRequest);
			System.out.println("UserGroupRelRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}