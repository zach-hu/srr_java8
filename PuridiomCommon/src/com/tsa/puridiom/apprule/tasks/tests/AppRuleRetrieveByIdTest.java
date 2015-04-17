package com.tsa.puridiom.apprule.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.apprule.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class AppRuleRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			AppRuleRetrieveById test = new AppRuleRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			AppRule appRule = (AppRule) test.executeTask(incomingRequest);
		
			System.out.println("AppRule: " + appRule.toString());
			System.out.println("AppRuleRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}