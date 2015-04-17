package com.tsa.puridiom.emails.tasks.tests;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

import java.io.File;
import java.util.*;

public class OfficeMaxRedirectTest
{
	public static void  main (String[] args) throws Exception
	{
		System.out.println("start");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader("bsc04p");
			PuridiomProcess process = processLoader.loadProcess("officemax-email-redirect.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", "bsc04p");
			incomingRequest.put("officeMaxEmail", new File("C:\\HiltonProjects\\emails\\officemax\\incoming\\11608652000000.txt"));

			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				System.out.println("success");
			}
			else
			{
				System.out.println("failed");
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		System.out.println("end");
	}

}