package com.tsa.puridiom.organization.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.organization.OrganizationManager;
import com.tsa.puridiom.organization.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class OrganizationRegisterNewTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("companyName", "Kelli's Test Org");
			OrganizationRegisterNew test = new OrganizationRegisterNew();

			String organizationId = (String) test.executeTask(incomingRequest);

			System.out.println("Organization Id: " + organizationId);

			System.out.println("Organization Valid: " + OrganizationManager.getInstance().isOrganizationValid(organizationId));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}