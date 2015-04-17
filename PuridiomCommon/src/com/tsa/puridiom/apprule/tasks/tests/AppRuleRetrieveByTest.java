package com.tsa.puridiom.apprule.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.apprule.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AppRuleRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			AppRuleRetrieveBy test = new AppRuleRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List appRuleList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < appRuleList.size(); i++)
			{
				AppRule appRule = (AppRule) appRuleList.get(i);
				System.out.println("AppRule: " + appRuleList.toString());
			}
		
			System.out.println("AppRuleRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}