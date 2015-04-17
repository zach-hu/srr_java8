package com.tsa.puridiom.labels.tasks.tests;

import com.tsa.puridiom.labels.tasks.LabelsRetrieveByLanguage;
import com.tsagate.foundation.database.DBSession;

import java.util.*;

public class LabelsRetrieveByLanguageTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	organizationId = "PX0001";
			DBSession dbsession = new DBSession(organizationId);

			Map incomingRequest = new HashMap();

			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("dbsession", dbsession);
			incomingRequest.put("Labels_language", "EN");

			LabelsRetrieveByLanguage test = new LabelsRetrieveByLanguage();

			List labelsList = (List) test.executeTask(incomingRequest);

			System.out.println("LabelsRetrieveByLanguage STATUS: " + test.getStatus());
			System.out.println("LabelsList size =  " + labelsList.size());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}