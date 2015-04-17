package com.tsa.puridiom.usergrouprel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.usergrouprel.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class UserGroupRelDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			UserGroupRelDeleteById test = new UserGroupRelDeleteById();
			Map incomingRequest = new HashMap();

			UserGroupRel userGroupRel = new UserGroupRel();
			UserGroupRelPK pk = new UserGroupRelPK();
			pk.setGroupId("REQUISITIONER");
			pk.setUserId("kelli@tsagate.com");
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
			System.out.println("UserProfileDeleteByIdTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}