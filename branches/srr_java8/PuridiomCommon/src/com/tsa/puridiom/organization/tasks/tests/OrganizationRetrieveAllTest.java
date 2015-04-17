package com.tsa.puridiom.organization.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.organization.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class OrganizationRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			OrganizationRetrieveAll test = new OrganizationRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List organizationList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < organizationList.size(); i++)
			{
				Organization organization = (Organization) organizationList.get(i);
				System.out.println("Organization: " + organizationList.toString());
			}
		
			System.out.println("OrganizationRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}