package com.tsa.puridiom.timer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class OfficemaxEmailsJob extends ReadInboxJob
{

	public void onStart()
	{
		this.setJobType("officemax");
	}
	
	public int processEmail(File emailFile, String subject, String sendFrom, String organizationId)
	{
		int success = Status.READY;
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("officeMaxEmail", emailFile);
			incomingRequest.put("officeMaxSubject", subject);
			incomingRequest.put("sendFrom", sendFrom);
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("officemax-email-redirect.xml");
			process.executeProcess(incomingRequest);
			success = process.getStatus();
		}
		catch (Exception exception)
		{
			success = Status.FAILED;
		}

		return success;
	}

}
