package com.tsa.puridiom.labels.tasks.tests;

import com.tsa.puridiom.labels.tasks.LabelsRetrieveByLanguage;
import com.tsa.puridiom.labels.tasks.LabelsWriteToPropertiesFile;
import com.tsagate.foundation.database.DBSession;

import java.util.*;

public class LabelsWriteToPropertiesFileTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			LabelsWriteToPropertiesFile test = new LabelsWriteToPropertiesFile();
			LabelsRetrieveByLanguage labelsRetrieve = new LabelsRetrieveByLanguage();

		    String	organizationId = "PX0001";
			DBSession dbsession = new DBSession(organizationId);

			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("dbsession", dbsession);
			incomingRequest.put("Labels_language", "EN");

			List labelsList = (List) labelsRetrieve.executeTask(incomingRequest);

			incomingRequest.put("labelsList", labelsList);

			test.executeTask(incomingRequest);

			System.out.println("LabelsWriteToPropertiesFile STATUS: " + test.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}