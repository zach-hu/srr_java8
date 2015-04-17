/**
 * 
 */
package com.tsa.puridiom.timer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class EFaxEmailsJob extends ReadInboxJob
{

	public void onStart()
	{
		this.setJobType("efaxemail");
	}

	public int processEmail(File emailFile, String subject, String sendFrom, String organizationId)
	{
		int success = Status.READY;

		try
		{
			Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("efax-email-process.xml");

			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("emailFile", emailFile);
			incomingRequest.put("subject", subject);
			incomingRequest.put("sendFrom", sendFrom);
			incomingRequest.put("jobType", HiltonUtility.ckNull(this.getJobType()));
			process.executeProcess(incomingRequest);

			success = process.getStatus();

		} catch (Exception exception)
		{
			Log.error(this, "EFaxEmailsJob error " + exception.getMessage());

			success = Status.FAILED;
		}

		return success;
	}

}