package com.tsa.puridiom.labels.tasks.tests;

import com.tsa.puridiom.labels.tasks.LabelsCreatePropertiesFile;

import java.io.File;
import java.util.*;

public class LabelsCreatePropertiesFileTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			LabelsCreatePropertiesFile test = new LabelsCreatePropertiesFile();
			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", "PX0001");
			incomingRequest.put("userId", "SYSADM");

			File newFile = (File) test.executeTask(incomingRequest);

			System.out.println("LabelsCreatePropertiesFile STATUS: " + test.getStatus());
			System.out.println("File created: " + newFile.getAbsoluteFile().getName());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}