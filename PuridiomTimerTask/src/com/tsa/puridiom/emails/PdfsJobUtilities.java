package com.tsa.puridiom.emails;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

public class PdfsJobUtilities extends Task
{

	public static String getExtraBcc(String organizationId, String sendFrom)
	{
		PdfsJobUtilities pdfUtils = new PdfsJobUtilities();
		Map userIncomingRequest = new HashMap();
		userIncomingRequest.put("organizationId", organizationId);
		userIncomingRequest.put("UserProfile_organizationId", organizationId);
		userIncomingRequest.put("UserProfile_mailId", sendFrom);
		UserProfile sendFromUserProfile;
		try {		sendFromUserProfile = (UserProfile)pdfUtils.executeTask(userIncomingRequest);	}
		catch (Exception e)
		{
			sendFromUserProfile =  new UserProfile();
			e.printStackTrace();
		}

		if (sendFromUserProfile == null)
		{
			return "";
		}
		else
		{
			return PdfsJobUtilities.getBcc(sendFromUserProfile, organizationId);
		}
	}

	public static String getBcc(UserProfile sendFromUserProfile, String organizationId)
	{
		String bcc = "";
		if(sendFromUserProfile.getLocale().equalsIgnoreCase("US"))
		{
			bcc = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.extra.bcc.us", "");
		}
		else if(sendFromUserProfile.getLocale().equalsIgnoreCase("CA"))
		{
			bcc = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.extra.bcc.ca", "");
		}
		else
		{
			bcc = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.extra.bcc", "");
		}

		return bcc;
	}
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
			HashMap processParameters = new HashMap();
            processParameters.put("organizationId", incomingRequest.get("organizationId"));
            processParameters.put("UserProfile_organizationId", incomingRequest.get("organizationId"));
            processParameters.put("UserProfile_mailId", incomingRequest.get("UserProfile_mailId"));

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by-mailid.xml");

            process.executeProcess(processParameters);

            UserProfile userProfile = (UserProfile) processParameters.get("userProfile");

            if (userProfile != null)
            {
            	ret = userProfile;
            }
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
