package com.tsa.puridiom.steporder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class ProcessStepsFactory
{

	public static List getCheckListEntry(String organizationId)
	{
		List checkList = null;
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("ChecklistEntry_referenceType", "POH");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("checklistentry-retrieve-by-referencetype.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				checkList = (List)incomingRequest.get("checklistEntryList");
			}
		}
		catch (Exception exception)
		{
			//TODO handle exception
		}

		return checkList;
	}
	public static ProcessSteps getProcessStep(String form, String type, String oid)
	{
		String xmlFile = ProcessStepsFactory.getProcessFile(form) + "_" + type.toLowerCase();

		return new ProcessSteps(xmlFile, oid);

	}

	public static String getProcessFile(String form)
	{
		String xmlFile = "posteps";

		if(form.equalsIgnoreCase("PO"))
		{
			 xmlFile = "posteps";
		}
		return xmlFile;
	}



}
