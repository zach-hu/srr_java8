package com.tsa.puridiom.userregistration.tasks.tests;

import com.tsa.puridiom.userregistration.tasks.*;
import java.util.*;

public class GenerateOrgIdFromCompanyNameTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			GenerateOrgIdFromCompanyName test = new GenerateOrgIdFromCompanyName();
			Map incomingRequest = new HashMap();
			String companyName = "Technical Services Associates";

			incomingRequest.put("companyName", companyName);

			String organizationId = (String) test.executeTask(incomingRequest);
			if (organizationId == null) {
				System.out.println("organizationId returned null for " + companyName);
			} else {
				System.out.println("organizationId = "+ String.valueOf(organizationId) + " [for " + companyName + "]");
			}

			companyName = "Shawnee Vinyl";

			incomingRequest.put("companyName", companyName);

			organizationId = (String) test.executeTask(incomingRequest);
			if (organizationId == null) {
				System.out.println("organizationId returned null for " + companyName);
			} else {
				System.out.println("organizationId = "+ String.valueOf(organizationId) + " [for " + companyName + "]");
			}

			System.out.println("GenerateOrgIdFromCompanyNameTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}