package com.tsa.puridiom.apprule.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.apprule.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AppRuleUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			AppRuleUpdate test = new AppRuleUpdate();
			Map incomingRequest = new HashMap();
		
			AppRule appRule = new AppRule();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("appRule", appRule);
		
			appRule = (AppRule) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("AppRuleUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("AppRuleUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}