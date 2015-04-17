package com.tsa.puridiom.usergrouprel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.usergrouprel.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class UserGroupRelUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserGroupRelUpdate test = new UserGroupRelUpdate();
			Map incomingRequest = new HashMap();

			UserGroupRel userGroupRel = new UserGroupRel();
			UserGroupRelPK pk = new UserGroupRelPK();
			pk.setGroupId("TESTGROUP");
			pk.setUserId("TESTUSER");
			userGroupRel.setComp_id(pk);
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("userGroupRel", userGroupRel);

			userGroupRel = (UserGroupRel) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("UserGroupRelUpdateTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}